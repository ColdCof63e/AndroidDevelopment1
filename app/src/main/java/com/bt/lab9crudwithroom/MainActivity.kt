package com.bt.lab9crudwithroom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.bt.lab9crudwithroom.ui.theme.Lab9CRUDwithRoomTheme
import com.bt.lab9crudwithroom.viewmodels.NoteViewModel
import com.bt.lab9crudwithroom.views.NotesApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab9CRUDwithRoomTheme {
                val viewModel = NoteViewModel(application)
                NotesApp(viewModel)
            }
        }
    }
}