package com.example.conatctslist.data.local

import androidx.lifecycle.LiveData
import com.example.conatctslist.data.local.db.ContactDao
import com.example.conatctslist.data.model.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactLocalDataSource(private val contactDao: ContactDao) : IContactLocalDataSource {

    override fun getContacts(): LiveData<List<Contact>> {
        return contactDao.getAllContacts()
    }

    override suspend fun insertContact(contact: Contact): Long {
        return withContext(Dispatchers.IO) {
            contactDao.insertContact(contact)
        }
    }

    override suspend fun deleteContact(contact: Contact): Int {
        return withContext(Dispatchers.IO) {
            contactDao.deleteContact(contact)
        }
    }
}