package com.example.btvn2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StudentDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "student.db", null, 1) {

    companion object {
        const val TABLE_NAME = "students"
        const val COL_ID = "id"
        const val COL_NAME = "name"
        const val COL_EMAIL = "email"
        const val COL_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COL_ID TEXT PRIMARY KEY,
                $COL_NAME TEXT,
                $COL_EMAIL TEXT,
                $COL_PHONE TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertStudent(student: StudentModel): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_ID, student.id)
            put(COL_NAME, student.name)
            put(COL_EMAIL, student.email)
            put(COL_PHONE, student.SĐT)
        }
        val result = db.insert(TABLE_NAME, null, values)
        return result != -1L
    }

    fun updateStudent(student: StudentModel): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COL_NAME, student.name)
            put(COL_EMAIL, student.email)
            put(COL_PHONE, student.SĐT)
        }
        val result = db.update(TABLE_NAME, values, "$COL_ID=?", arrayOf(student.id))
        return result > 0
    }

    fun deleteStudent(id: String): Boolean {
        val db = writableDatabase
        val result = db.delete(TABLE_NAME, "$COL_ID=?", arrayOf(id))
        return result > 0
    }

    fun getAllStudents(): List<StudentModel> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val students = mutableListOf<StudentModel>()
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getString(cursor.getColumnIndexOrThrow(COL_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME))
                val email = cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL))
                val phone = cursor.getString(cursor.getColumnIndexOrThrow(COL_PHONE))
                students.add(StudentModel(name, id, email, phone))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return students
    }
}
