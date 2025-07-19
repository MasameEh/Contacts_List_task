package com.example.contactslist.data.repo

import androidx.lifecycle.LiveData
import com.example.contactslist.data.model.Contact

interface IContactRepository {

    fun getContacts(): LiveData<List<Contact>>
    suspend fun insertContact(contact: Contact): Long
    suspend fun deleteContact(contact: Contact): Int
}