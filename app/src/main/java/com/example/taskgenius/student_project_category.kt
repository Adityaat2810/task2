package com.example.taskgenius

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class student_project_category : AppCompatActivity() {

    private lateinit var addSoftwareProject:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_project_category)

        addSoftwareProject=findViewById(R.id.software)

        addSoftwareProject.setOnClickListener{
            val intent = Intent(this,student_add_project::class.java)
            startActivity(intent)
        }

    }
}