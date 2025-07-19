package com.example.conatctslist.data.local

import androidx.lifecycle.LiveData
import com.example.conatctslist.data.model.Contact

interface IContactLocalDataSource {

    fun getContacts(): LiveData<List<Contact>>
    suspend fun insertContact(contact: Contact): Long
    suspend fun deleteContact(contact: Contact): Int

}