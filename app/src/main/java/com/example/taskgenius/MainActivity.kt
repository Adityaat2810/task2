package com.example.taskgenius

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private lateinit var btnStudent:ImageButton
    private lateinit var btnRecruiter:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


        btnStudent=findViewById(R.id.btnStudent)
        btnRecruiter=findViewById(R.id.btnRecruiter)

        btnStudent.setOnClickListener{
            val intent = Intent(this,student_login::class.java)
            startActivity(intent)
            finish()

        }

    }
}