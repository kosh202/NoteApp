package com.example.noteapp

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1) //versionamento e incremento da base
//mapeia toda as tabelas da base
//não pode ser instanciada
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}