package com.example.btvn2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var lvSV: ListView
    private lateinit var adapter: StudentAdapter
    private lateinit var db: StudentDatabaseHelper
    private var students = mutableListOf<StudentModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        lvSV = findViewById(R.id.lvSV)
        db = StudentDatabaseHelper(this)

        students = db.getAllStudents().toMutableList()
        adapter = StudentAdapter(this, students)
        lvSV.adapter = adapter


        lvSV.setOnItemClickListener { _, _, position, _ ->
            val student = students[position]
            val intent = Intent(this, StudentActivity::class.java).apply {
                putExtra("edit_mode", true)
                putExtra("name", student.name)
                putExtra("id", student.id)
                putExtra("email", student.email)
                putExtra("phone", student.SĐT)
                putExtra("position", position)
            }
            startActivityForResult(intent, 1)
        }

        findViewById<Toolbar>(R.id.toolbar).setOnClickListener {
            val intent = Intent(this, StudentActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            val student = StudentModel(
                data.getStringExtra("name")!!,
                data.getStringExtra("id")!!,
                data.getStringExtra("email")!!,
                data.getStringExtra("phone")!!
            )
            val isEdit = data.getBooleanExtra("edit_mode", false)
            val position = data.getIntExtra("position", -1)

            if (isEdit && position >= 0) {
                db.updateStudent(student)
                students[position] = student
            } else {
                db.insertStudent(student)
                students.add(student)
            }
            adapter.notifyDataSetChanged()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mnuAddSV -> {
                Toast.makeText(this, "Click Thêm sinh viên", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, StudentActivity::class.java)
                startActivityForResult(intent, 1)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
