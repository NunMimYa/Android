package com.formation.notepad

import android.content.Context
import android.util.Log
import com.formation.agrikoliskotlin.Note
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.*

private val TAG = "storage"

fun persistNote(context: Context, note: Note){
    if (note.filename.isEmpty()){
        note.filename = UUID.randomUUID().toString() + ".note"
    }

    Log.i(TAG, "Saving note : $note")

    val fileOutput = context.openFileOutput(note.filename, Context.MODE_PRIVATE)

    val outputStream = ObjectOutputStream(fileOutput)
    outputStream.writeObject(note)
    outputStream.close()
}

fun findAll(context: Context) : MutableList<Note>{
    val notes = mutableListOf<Note>()

    val notesDir = context.filesDir

    for (filename in notesDir.list().reversed()){
//    for (filename in notesDir.list()){
        val note = findOne(context, filename)
        Log.i(TAG, "Read note : $note")
        notes.add(note)
    }

    return notes
}

fun deleteFile(context: Context, note: Note){
    context.deleteFile(note.filename)
}

private fun findOne(context: Context, filename: String): Note {
    val fileInput = context.openFileInput(filename)
    val inputStream = ObjectInputStream(fileInput)
    val note = inputStream.readObject() as Note
    inputStream.close()
    return note
}






