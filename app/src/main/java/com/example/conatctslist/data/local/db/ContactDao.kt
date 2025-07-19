package com.example.conatctslist.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.conatctslist.data.model.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM contact ORDER BY name ASC")
    fun getAllContacts(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertContact(contact: Contact): Long

    @Delete
    suspend fun deleteContact(contact: Contact): Int
}