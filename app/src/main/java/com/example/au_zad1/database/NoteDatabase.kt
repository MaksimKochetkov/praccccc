package com.example.au_zad1.database
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.au_zad1.model.Note
import com.example.au_zad1.model.NoteDao

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
    companion object {
        @Volatile
        private var instance: NoteDatabase? = null
        null private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?:

        synchronized(LOCK){
            instance ?:
            createDatabase(context).also{ it: NoteDatabase
                instance = it
            }
        }
    }
    private fun createDatabase(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase:: class.java,
            name: "note_db"
    ).build()