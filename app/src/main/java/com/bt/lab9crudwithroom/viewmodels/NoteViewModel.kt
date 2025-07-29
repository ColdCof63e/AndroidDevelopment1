package com.bt.lab9crudwithroom.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.bt.lab9crudwithroom.models.DatabaseProvider
import com.bt.lab9crudwithroom.models.Note
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = DatabaseProvider.getDatabase(application).noteDao()

    val notes = dao.getAllNotes().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList<Note>()
    )

    fun addNote(note: Note) = viewModelScope.launch {
        dao.insertNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        dao.deleteNote(note)
    }

    fun clearAll() = viewModelScope.launch {
        dao.clearAll()
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        dao.updateNote(note)
    }

}