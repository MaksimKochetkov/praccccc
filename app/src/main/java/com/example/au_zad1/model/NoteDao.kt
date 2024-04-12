package com.example.au_zad1.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note)
}
@Update
suspend fun updateNote(note:Note)

@Delete
suspend fun deleteNote(note:Note)

@Query ("SELECT * FROM NOTES ORDER BY id DESC")
suspend fun getAllNotes(): LiveData<List<Note>>

@Query ("SELECT * FROM NOTES WHERE noteTitle LIKE :query OR noteDesc LIKE :query")
suspend fun searchNote(query: String?): LiveData<List<Note>>