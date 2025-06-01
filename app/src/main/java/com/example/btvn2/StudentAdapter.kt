package com.example.btvn2

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class StudentAdapter(val activity: Activity, val studentList: List<StudentModel>) : ArrayAdapter<StudentModel>(activity, R.layout.list_item)
{
    override fun getCount(): Int {
        return studentList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val context = activity.layoutInflater
        val rowView = context.inflate(R.layout.list_item, parent,false)
        val img = rowView.findViewById<ImageView>(R.id.imageView)
        val name = rowView.findViewById<TextView>(R.id.text_hoten)
        val id = rowView.findViewById<TextView>(R.id.text_mssv)

        val student = studentList[position]
        name.text = student.name
        id.text = student.id
        img.setImageResource(R.drawable.ic_launcher_foreground)

        return rowView
    }
}