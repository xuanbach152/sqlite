package com.example.btvn2
import android.provider.ContactsContract.CommonDataKinds.Phone
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentModel(
    val name: String,
    @PrimaryKey val id: String,
    val email: String,
    val phone: String
)
