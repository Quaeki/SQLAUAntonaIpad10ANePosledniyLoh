package com.kaiser.sqlauantonaipad10aneposledniyloh

import androidx.room.Room

object SingletoneDB {
    val db = Room.databaseBuilder(
        MainActivity.getContext(),
        AppDataBase::class.java, "belka1"
    ).build()
}