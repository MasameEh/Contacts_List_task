package com.example.conatctslist.data.repo

import com.example.conatctslist.data.local.IContactLocalDataSource
import com.example.conatctslist.data.model.Contact

class ContactRepository(private val contactLocalDataSource: IContactLocalDataSource) : IContactRepository {


    override fun getContacts() = contactLocalDataSource.getContacts()

    override suspend fun insertContact(contact: Contact) = contactLocalDataSource.insertContact(contact)

    override suspend fun deleteContact(contact: Contact) = contactLocalDataSource.deleteContact(contact)
}