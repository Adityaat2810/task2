package com.example.taskgenius

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class student_login : AppCompatActivity() {
    private lateinit var signUpbtn: Button
    private lateinit var loginbtn: Button

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_login)
        supportActionBar?.hide()
// main activity hi login activity hai
        signUpbtn = findViewById(R.id.student_signup_button)
        signUpbtn.setOnClickListener {
            val intent = Intent(this, signup_student::class.java)
            startActivity(intent)
            finish()
        }

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.etd_password)

        mAuth = FirebaseAuth.getInstance()


        loginbtn = findViewById(R.id.student_login_button)

        loginbtn.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email, password);
        }


    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // log in user
                    val intent = Intent(this@student_login, student_entry::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(
                        this@student_login, "User does not exist", Toast.LENGTH_SHORT
                    ).show()

                }
            }

    }
}