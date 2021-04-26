package com.formation.agrikoliskotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.formation.notepad.Database
import com.formation.notepad.deleteFile
import com.formation.notepad.findAll
import com.formation.notepad.persistNote
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class NoteListActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var notes: MutableList<Note>
    lateinit var adapter: NoteAdapter

    lateinit var coordinateLayout: CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)

        findViewById<FloatingActionButton>(R.id.create_note_fab).setOnClickListener(this)

        coordinateLayout = findViewById(R.id.coordinate_layout)

        // findAll lecture dans le dossier
        notes = findAll(this)
        // findAll db
        // val db = Database(this)
        // notes = db.findAll()

        adapter = NoteAdapter(notes, this)

        val recyclerView = findViewById<RecyclerView>(R.id.notes_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onClick(v: View) {
        if (v.tag != null) {
            showNoteDetail(v.tag as Int)
        } else {
            when (v.id) {
                R.id.create_note_fab -> createNewNote()
            }
        }
    }

    private fun createNewNote() {
        showNoteDetail(-1)
    }

    private fun showNoteDetail(position: Int) {
        val note = if (position < 0) Note() else notes[position]

        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE, note as Parcelable)
        intent.putExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, position)
        startActivityForResult(intent, NoteDetailActivity.REQUEST_EDIT_NOTE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK || data == null) {
            return
        }

        when (requestCode) {
            NoteDetailActivity.REQUEST_EDIT_NOTE -> processEditNoteResult(data)
        }

    }

    private fun processEditNoteResult(data: Intent) {
        val noteIndex = data.getIntExtra(NoteDetailActivity.EXTRA_NOTE_INDEX, -1)

        when (data.action) {
            NoteDetailActivity.ACTION_SAVE_NOTE -> {
                val note = data.getParcelableExtra<Note>(NoteDetailActivity.EXTRA_NOTE)!!
                saveNote(note, noteIndex)
            }
            NoteDetailActivity.ACTION_DELETE_NOTE -> {
                deleteNote(noteIndex)
            }
        }
    }

    private fun deleteNote(noteIndex: Int) {
        if (noteIndex < 0){
            return
        }
        val note = notes[noteIndex]
        // delete the file
        deleteFile(this, note)
        // delete in db
        val db = Database(this)
        db.deleteNote(note)

        Snackbar.make(coordinateLayout, "${note.title} supprimé", Snackbar.LENGTH_SHORT)
            .show()
        notes.removeAt(noteIndex)
        adapter.notifyDataSetChanged()
    }

    private fun saveNote(note: Note, noteIndex: Int) {
        // persister dans un fichier
        persistNote(this, note)
        // save and create db
        val db = Database(this)

        if (noteIndex < 0) {
            notes.add(0, note)
            db.createNote(note)
        } else {
            notes[noteIndex] = note
            db.updateNote(note)
        }
        adapter.notifyDataSetChanged()
    }
}