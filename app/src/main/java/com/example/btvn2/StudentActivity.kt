package com.example.btvn2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class StudentActivity : AppCompatActivity() {

    private var isEditMode = false
    private var position = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update)

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val edtName = findViewById<EditText>(R.id.edtName)
        val edtId = findViewById<EditText>(R.id.edtId)
        val edtEmail = findViewById<EditText>(R.id.edtEmail)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)


        isEditMode = intent.getBooleanExtra("edit_mode", false)

        if (isEditMode) {
            tvTitle.text = "Cập nhật sinh viên"
            edtName.setText(intent.getStringExtra("name"))
            edtId.setText(intent.getStringExtra("id"))
            edtEmail.setText(intent.getStringExtra("email"))
            edtPhone.setText(intent.getStringExtra("phone"))
            position = intent.getIntExtra("position", -1)
        } else {

            tvTitle.text = "Thêm sinh viên mới"
        }


        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val name = edtName.text.toString()
            val id = edtId.text.toString()
            val email = edtEmail.text.toString()
            val phone = edtPhone.text.toString()

            if (name.isEmpty() || id.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultIntent = Intent()
            resultIntent.putExtra("name", name)
            resultIntent.putExtra("id", id)
            resultIntent.putExtra("email", email)
            resultIntent.putExtra("phone", phone)
            resultIntent.putExtra("position", position)
            resultIntent.putExtra("edit_mode", isEditMode)

            setResult(RESULT_OK, resultIntent)
            finish()
        }

        findViewById<Button>(R.id.btnCancel).setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}