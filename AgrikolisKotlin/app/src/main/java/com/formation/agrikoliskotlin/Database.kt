package com.formation.notepad

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.formation.agrikoliskotlin.Note

class Database(context: Context) : SQLiteOpenHelper(context, "liste.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE note (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}


    fun createNote(note: Note){
        val values = ContentValues()
        values.put("title", note.title)
        values.put("description", note.text)

        writableDatabase.insert("note", null, values)
    }

    fun findAll() : MutableList<Note> {
        val notes = mutableListOf<Note>()

        val cursor = readableDatabase.rawQuery("SELECT * FROM note", null)

        while (cursor.moveToNext()){
            val note = Note(
                cursor.getString(cursor.getColumnIndex("title")),
                cursor.getString(cursor.getColumnIndex("description"))
            )
            notes.add(note)
        }
        cursor.close()
        return notes
    }

    fun deleteNote(note: Note){
        writableDatabase.delete("note", "title = ?", arrayOf(note.title))
    }

    fun updateNote(note: Note){
        val values = ContentValues()
        values.put("title", note.title)
        values.put("description", note.text)

        writableDatabase.update("note", values,"title = ?", arrayOf(note.title))
    }


}