package com.example.noteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.noteapp.databinding.ActivityNewNoteBinding
import kotlin.concurrent.thread

class NewNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonInsert.setOnClickListener {
            insert()
        }
    }
    fun insert(){
        //obter uma instancia do room
        var db = Room.databaseBuilder(this, NoteDatabase::class.java, "notes")//notes.db aparece automatico
            .build()

        //criando uma instancia de entidade
        val note = Note(title = binding.editTitle.text.toString(),
            desc = binding.editDesc.text.toString())

        //chamando a funcao do dao para insercao
        Thread {
            db.noteDao().insert(note)
        }.start()
    }
}