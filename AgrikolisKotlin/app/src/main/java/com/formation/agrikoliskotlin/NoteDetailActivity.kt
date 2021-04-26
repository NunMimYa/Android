package com.formation.agrikoliskotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NoteDetailActivity : AppCompatActivity() {

    companion object {
        val REQUEST_EDIT_NOTE = 1
        val EXTRA_NOTE = "note"
        val EXTRA_NOTE_INDEX = "noteIndex"

        val ACTION_SAVE_NOTE = "com.formation.notepad.actions.ACTION_SAVE_NOTE"
        val ACTION_DELETE_NOTE = "com.formation.notepad.actions.ACTION_DELETE_NOTE"
    }

    lateinit var note: Note
    var noteIndex: Int = -1

    lateinit var title: TextView
    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)
//
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        title = findViewById(R.id.title)
        text = findViewById(R.id.text)

        note = intent.getParcelableExtra<Note>(EXTRA_NOTE)!!
        noteIndex = intent.getIntExtra(EXTRA_NOTE_INDEX, -1)

        title.text = note.title
        text.text = note.text
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_note_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_save -> {
                saveNote()
                true
            }
            R.id.action_delete -> {
                showConfirmDeleteNoteDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showConfirmDeleteNoteDialog() {
        val confirmDialog = ConfirmDeleteDialogFragment(note.title)
        confirmDialog.listener = object : ConfirmDeleteDialogFragment.ConfirmDeleteDialogListener {
            override fun onDialogPositiveClick() {
                deleteNote()
            }

            override fun onDialogNegativeClick() {}
        }

        confirmDialog.show(supportFragmentManager, "confirmDeleteDialog")
    }

    private fun deleteNote() {
        intent = Intent(ACTION_DELETE_NOTE)
        intent.putExtra(EXTRA_NOTE_INDEX, noteIndex)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun saveNote() {
        note.title = title.text.toString()
        note.text = text.text.toString()

        intent = Intent(ACTION_SAVE_NOTE)
        intent.putExtra(EXTRA_NOTE, note as Parcelable)
        intent.putExtra(EXTRA_NOTE_INDEX, noteIndex)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}