package com.example.taskgenius

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signup_student : AppCompatActivity() {
    private lateinit var edt_name: EditText
    private lateinit var edt_clg_name: EditText
    private lateinit var edt_email: EditText
    private lateinit var edt_password: EditText
    private lateinit var btnSignUp: Button

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference



        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_signup_student)

            supportActionBar?.hide()
            mAuth = FirebaseAuth.getInstance()

            edt_name = findViewById(R.id.etd_name)
            edt_clg_name = findViewById(R.id.etd_clg_name)
            edt_email = findViewById(R.id.edt_email)
            edt_password = findViewById(R.id.etd_password)
            btnSignUp = findViewById(R.id.student_signup_button)

            btnSignUp.setOnClickListener {
                val name = edt_name.text.toString()
                val email = edt_email.text.toString()
                val password = edt_password.text.toString()
                val college = edt_clg_name.text.toString()

                funSignUp(name, email, password, college)

            }


        }

        private fun funSignUp(name: String, email: String, password: String, college: String) {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //code for jumping to home
                        //add user to database
                        addUserToDatabase(name, college, email, mAuth.currentUser?.uid!!)

                        val intent = Intent(this@signup_student, student_entry::class.java)
                        intent.putExtra("name", name)
                        finish()
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            this@signup_student, "Some error occured", Toast.LENGTH_SHORT
                        ).show()
                    }
                }


        }

        private fun addUserToDatabase(name: String, email: String,college: String, uid: String) {
            mDbRef= FirebaseDatabase.getInstance().getReference("users")
            //mDbRef.child("users").child("user").child("students").child(uid).setValue(student(name,email,college, uid))
            mDbRef.child("user").child(uid).setValue(student(name,email,college, uid))


        }

    }
