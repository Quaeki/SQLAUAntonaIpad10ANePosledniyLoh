package com.kaiser.sqlauantonaipad10aneposledniyloh

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kaiser.sqlauantonaipad10aneposledniyloh.ui.theme.Belka


@Database(entities = arrayOf(Belka::class), version = 1)
abstract class AppDataBase:RoomDatabase() {
    abstract fun belkaDAO():BelkaDAO
}