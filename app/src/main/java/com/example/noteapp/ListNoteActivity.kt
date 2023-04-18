package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.noteapp.databinding.ActivityListNoteBinding
import com.example.noteapp.databinding.ActivityNewNoteBinding
import com.example.noteapp.databinding.NoteBinding

class ListNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityListNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityListNoteBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        refreshNotes()
    }

    fun refreshNotes(){
        val db = Room
            .databaseBuilder(this,NoteDatabase::class.java,"notes")
            .build()

            Thread{
                val notes = db.noteDao().list()
                runOnUiThread {
                    refreshUI(notes)
                }
            }.start()
    }

    fun refreshUI(notes: List<Note>){

        notes.forEach {
            val noteBinding = NoteBinding.inflate(layoutInflater)

            noteBinding.textTitle.text = it.title
            noteBinding.textDesc.text = it.desc

            binding.conteiner.addView(noteBinding.root)
        }
    }
}