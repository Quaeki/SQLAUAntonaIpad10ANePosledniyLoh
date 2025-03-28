package com.kaiser.sqlauantonaipad10aneposledniyloh.ui.theme

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Belka(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
//    @ColumnInfo(name = "bugaga")
    val taleColor:String,
    val name:String
)