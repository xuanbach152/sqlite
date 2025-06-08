package com.example.btvn2
import androidx.room.*

@Dao
interface StudentDao {
    @Query("SELECT * FROM students")
    fun getAllStudents(): List<StudentModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStudent(student: StudentModel)

    @Update
    fun updateStudent(student: StudentModel)

    @Delete
    fun deleteStudent(student: StudentModel)
}
