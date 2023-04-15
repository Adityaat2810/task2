package com.example.taskgenius

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.taskgenius.databinding.ActivityStudentAddProjectBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream

class student_add_project : AppCompatActivity() {

    var sImage:String?=""
    private lateinit var binding:ActivityStudentAddProjectBinding
    private lateinit var db: DatabaseReference
    private lateinit var mAuth: FirebaseAuth

    private lateinit var addImage:Button
    private lateinit var insertData:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityStudentAddProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
         addImage=findViewById(R.id.addImage)
        insertData=findViewById(R.id.addProject)

        addImage.setOnClickListener{
            insertImage(View(this))
        }
        insertData.setOnClickListener{
            insertData(View(this))
        }


    }

    fun insertData(view: View) {
        val projectTitle=binding.projectName.text.toString()
        val projectDescription=binding.projectDescription.text.toString()
        val projectTechStack=binding.techStack.text.toString()
        val uid=mAuth.currentUser?.uid!!
        db= FirebaseDatabase.getInstance().getReference("users").child("user")
            .child(uid).child("projects")
        val item=itemds(projectTitle,projectDescription,
            projectTechStack,sImage
        )
        val databaseReference= FirebaseDatabase.getInstance().reference
        val id=databaseReference.push().key

        db.child(id.toString()).setValue(item).addOnSuccessListener {
            binding.projectName.text.clear()
            binding.projectDescription.text.clear()
            binding.techStack.text.clear()
            Toast.makeText(this,"data Inserted", Toast.LENGTH_SHORT)
                .show()
        }.addOnFailureListener{
            Toast.makeText(this,"data not Inserted", Toast.LENGTH_SHORT)
                .show()
        }

    }
    fun insertImage(view: View) {
        var myfileintent= Intent(Intent.ACTION_GET_CONTENT)
        myfileintent.setType("image/*")
        ActivityResultLauncher.launch(myfileintent)

    }

    private val ActivityResultLauncher=registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ){
            result: ActivityResult ->
        if (result.resultCode==RESULT_OK){
            val uri= result.data!!.data
            try {
                val inputStream = contentResolver.openInputStream(uri!!)
                val myBitmap= BitmapFactory.decodeStream(inputStream)
                val stream= ByteArrayOutputStream()
                myBitmap.compress(Bitmap.CompressFormat.PNG,100,stream)
                val bytes = stream.toByteArray()
                sImage= Base64.encodeToString(bytes, Base64.DEFAULT)
                binding.projectImage.setImageBitmap(myBitmap)
                inputStream!!.close()
                Toast.makeText(this,"Image selected ", Toast.LENGTH_SHORT)
                    .show()


            }catch(ex: Exception){
                Toast.makeText(this,ex.message.toString(),
                    Toast.LENGTH_LONG).show()
            }
        }
    }

}