package com.example.au_zad1.viewmodel
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.au_zad1.repository.NoteRepository
class NoteViewModelFactory(val app:Application, val noteRepository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return NoteViewModel(app, noteRepository) as T
}
}