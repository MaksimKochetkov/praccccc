package com.example.au_zad1.repository
import com.example.au_zad1.database. NoteDatabase import com.example.au_zad1.model.Note
class NoteRepository (private val db: NoteDatabase){
    suspend fun insertNote(note: Note) = db.getNoteDao().insertNote(note)
    suspend fun deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
    suspend fun updatettNote(note:Note) = db.getNoteDao().updateNote(note)
    fun getAllNotes()= db.getNoteDao().getAllNotes()
    fun searchNote(query: String?) = db.getNoteDao().searchNote(query)