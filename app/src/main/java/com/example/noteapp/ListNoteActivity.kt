package com.example.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.example.noteapp.databinding.ActivityListNoteBinding
import com.example.noteapp.databinding.ActivityNewNoteBinding
import com.example.noteapp.databinding.NoteBinding
import kotlin.concurrent.thread

class ListNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityListNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityListNoteBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.fab.setOnClickListener{
            val i = Intent(this, NewNoteActivity::class.java)
            startActivity(i)
        }
    }

    override fun onResume() {
        super.onResume()

        refreshNotes()
    }

    fun refreshNotes(){
        val db = Room.databaseBuilder(this,NoteDatabase::class.java,"notes")
            .build()

            Thread{
                val notes = db.noteDao().list()
                runOnUiThread {
                    refreshUI(notes)
                }
            }.start()
    }

    fun refreshUI(notes: List<Note>){
        binding.conteiner.removeAllViews()

        notes.forEach {currentNote->
            val noteBinding = NoteBinding.inflate(layoutInflater)

            noteBinding.textTitle.text = currentNote.title
            noteBinding.textDesc.text = currentNote.desc

            noteBinding.imageDelete.setOnClickListener{
                confirmDelet(currentNote)
            }


            binding.conteiner.addView(noteBinding.root)
        }
    }

    fun confirmDelet(note:Note){
        AlertDialog.Builder(this).setTitle("Confirmar Exclusao")
            .setMessage("deseja excluir ${note.title}? permamentemente?")
            .setNegativeButton("Nao",null)
            .setPositiveButton("Sim") {dialog, op ->
                delete(note)
            }
            .create()
            .show()
    }

    fun delete(note: Note){
        Thread {
            val db = Room.databaseBuilder(this,
            NoteDatabase::class.java,
            "notes")
                .build()

            db.noteDao().delete(note)
            refreshNotes()
        }.start()
    }
}