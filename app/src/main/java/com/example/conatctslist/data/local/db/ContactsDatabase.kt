package com.example.conatctslist.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.conatctslist.data.model.Contact


@Database(entities = [Contact::class], version = 1)
abstract class ContactsDatabase: RoomDatabase() {

    abstract fun getContactDao(): ContactDao

    companion object {
        const val DATABASE_NAME = "contacts_database"

        @Volatile
        private var instance: ContactsDatabase?  = null

        fun getInstance(context: Context): ContactsDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(context, ContactsDatabase::class.java, DATABASE_NAME).build().also { instance = it }
            }
        }
    }
}