package com.example.conatctslist.data.repo

import androidx.lifecycle.LiveData
import com.example.conatctslist.data.model.Contact

interface IContactRepository {

    fun getContacts(): LiveData<List<Contact>>
    suspend fun insertContact(contact: Contact): Long
    suspend fun deleteContact(contact: Contact): Int
}