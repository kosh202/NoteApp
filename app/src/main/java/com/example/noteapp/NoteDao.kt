package com.example.noteapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)
    @Query("SELECT * FROM Note")//seleciona tudo
    fun list(): List<Note>
 /*
    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM Note WHERE id = :id LIMIT 1")//traz somente 1
    fun get(id: Int): Note
    */
}