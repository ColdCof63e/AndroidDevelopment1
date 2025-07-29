package com.bt.lab9crudwithroom.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bt.lab9crudwithroom.models.Note
import com.bt.lab9crudwithroom.viewmodels.NoteViewModel

@Composable
fun NotesApp(viewModel: NoteViewModel) {
    val notes by viewModel.notes.collectAsState(emptyList<Note>())

    Scaffold(
        topBar = { NotesTopBar("Narendra - N01690273\nNotes App") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxWidth()
        )
        {
            AddNoteScreen(viewModel)
            Spacer(modifier = Modifier.height(16.dp))
            NotesList(notes, viewModel)
        }
    }
}

@Composable
fun NotesList(notes: List<Note>, viewModel: NoteViewModel) {
    LazyColumn {
        items(notes) {note ->
            var title by remember { mutableStateOf("") }
            var content by remember { mutableStateOf("") }
            var isEditing by remember { mutableStateOf(false) }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    if (isEditing) {
                        TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
                        TextField(value = content, onValueChange = { content = it }, label = { Text("Content") })

                        Button(onClick = {
                            viewModel.updateNote(note.copy(title = title, content = content))
                            isEditing = false
                        }) {
                            Text("Save")
                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        Button(onClick = { isEditing = false }) {
                            Text("Cancel")
                        }

                    } else {
                        Text("${note.title} - ${note.content}")
                        Spacer(modifier = Modifier.height(4.dp))

                        Button(onClick = { isEditing = true }) {
                            Text("Edit")
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))
                    Button(onClick = { viewModel.deleteNote(note) }) {
                        Text("Delete")
                    }
                }
            }
        }
    }
}

@Composable
fun AddNoteScreen(viewModel: NoteViewModel) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Column{
            Text(text = "Title")
            TextField(value = title, onValueChange = { title = it })
        }

        Column{
            Text(text = "Content")
            TextField(value = content, onValueChange = { content = it })
        }

        Button(
            onClick = {
                viewModel.addNote(Note(title = title, content = content))
            }) {
            Text(text = "Add Note")
        }
    }
}