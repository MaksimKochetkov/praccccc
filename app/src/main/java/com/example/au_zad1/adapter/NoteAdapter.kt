package com.example.au_zad1.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.au_zad1.databinding.NoteLayoutBinding
import com.example.au_zad1.adapter.fragments.HomeFragmentDirections
import com.example.au_zad1.adapter.model.Note
import com.example.au_zad1.fragments.HomeFragmentDirections
import com.example.au_zad1.model.Note

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    class NoteViewHolder(val itemBinding: NoteLayoutBinding): RecyclerView. ViewHolder(itenBinding.root)
        private val differCallback = object : DiffUtil.ItemCallback<Note>()
        {
            override fun areItemsTheSame(olditem: Note, newitem: Note): Boolean {
                return oldItem.id == newitem.id &&
                        oldItem.noteDesc == newitem.noteDesc && oldIten.noteTitle newItem.noteTitle
                        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == nеwItem
            }
        }
        val differ = AsyncListDiffer(adapter: this, differCalback)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViemtHolder {
            return NoteViewHolder(
                NoteLayoutBinding.inflate(LayoutInflater.from(parent.context))
            )
        }
        override fun getItemCount(): Int {
            return differ.currentList.size
        }
        override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
            val currentNote = differ.currentList[position]
            holder.itemBinding.noteTitle.text = currentNote.noteTitle
            holder.itemBinding.noteDesc.text = currentNote.noteDesc
            holder.itemView.setOnclicklistener {
                val direction = HomeFragmentDirections.actionHomeFragmentToEditNoteFragment(currentNote)
                it.findNavController().navigate(direction)
            }}}
