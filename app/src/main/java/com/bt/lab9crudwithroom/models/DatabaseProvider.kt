package com.bt.lab9crudwithroom.models

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    fun getDatabase(context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note_database"
        ).build()
    }
}