package com.example.conatctslist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey val phoneNumber: String,
    val name: String
)
