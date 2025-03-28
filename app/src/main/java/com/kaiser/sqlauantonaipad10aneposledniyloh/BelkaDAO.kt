package com.kaiser.sqlauantonaipad10aneposledniyloh

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kaiser.sqlauantonaipad10aneposledniyloh.ui.theme.Belka

@Dao
interface BelkaDAO {
    @Query("SELECT * FROM Belka")
    fun grtAllBelka():List<Belka>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBelka(belka:Belka):Unit

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateBelka(belka:Belka):Unit

    @Delete
    fun deleteBelka(belka:Belka):Unit
}