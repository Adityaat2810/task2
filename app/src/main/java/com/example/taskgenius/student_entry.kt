package com.example.taskgenius

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class student_entry : AppCompatActivity() {

    private lateinit var addProject:ImageButton
    private lateinit var viewProject:ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_entry)

        supportActionBar?.hide()

        addProject=findViewById(R.id.addProject)
        viewProject=findViewById(R.id.viewProject)


        addProject.setOnClickListener{
            val intent= Intent(this,student_project_category::class.java)
            startActivity(intent)
        }

        viewProject.setOnClickListener{
            val intent= Intent(this,student_view_project::class.java)
            startActivity(intent)
        }





    }
}