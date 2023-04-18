package com.example.noteapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,//? é opcional
    var title: String,//obrigatorio
    @ColumnInfo(name="description")//customizar
    var desc: String

    )
