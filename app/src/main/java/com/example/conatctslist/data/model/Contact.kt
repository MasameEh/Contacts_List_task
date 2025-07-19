package com.example.conatctslist.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey val phoneNumber: String,
    val name: String
) : Parcelable
