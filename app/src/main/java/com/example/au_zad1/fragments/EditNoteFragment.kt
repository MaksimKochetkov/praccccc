package com.example.au_zad1.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.au_zad1.MainActivity
import com.example.au_zad1.R
import com.example.au_zad1.databinding.FragmentEditNoteBinding
import com.example.au_zad1.model.Note
import com.example.au_zad1.viewmodel.NoteViewModel

class EditNoteFragment : Fragment(R.layout.fragment_edit_note), MenuProvider {
    private var editNoteBinding: FragmentEditNoteBinding ?= null
    private val binding get() = editNoteBinding!!
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var currentNote: Note
    private val args: EditNoteFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        editNoteBinding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        notesViewModel = (activity as MainActivity).noteViewModel
        currentNote = args.note!!
        binding.editNoteTitle.setText(currentNote.noteTitle)
        binding.editNoteDesc.setText(currentNote.noteDesc)
        binding.editNoteFab.setOnClickListener {
            val noteTitle = binding.editNoteTitle.text.toString().trim()
            val noteDesc = binding.editNoteDesc.text.toString().trim()
            if (noteTitle.isNotEmpty()) {
                val note = Note(currentNote.id, noteTitle, noteDesc)
                notesViewModel.updateNote(note)
                view.findNavController().popBackStack(R.id.homeFragment2, false)

            }
            else {
                Toast.makeText(context, "Введите название заметки", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun deleteNote() {
        AlertDialog.Builder(activity).apply {
            setTitle("Удаление заметки")
            setMessage("Вы точно хотите удалить заметку?")
            setPositiveButton("Удалить") { _,_ ->
                notesViewModel.deleteNote(currentNote)
                Toast.makeText(context, "Заметка удалена", Toast.LENGTH_SHORT).show()
                view?.findNavController()?.popBackStack(R.id.homeFragment2, false)
            }
            setNegativeButton("Отмена", null)
        }.create().show()
    }
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_edit_note, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.deleteMenu -> {
                deleteNote()
                true
            }
            else -> false
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        editNoteBinding = null
    }
}