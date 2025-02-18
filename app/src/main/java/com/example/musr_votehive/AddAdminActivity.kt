package com.example.musr_votehive

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class AddAdminActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_admin)

        auth = FirebaseAuth.getInstance()

        val email = findViewById<EditText>(R.id.admin_email)
        val password = findViewById<EditText>(R.id.admin_password)
        val addAdminButton = findViewById<Button>(R.id.btn_add_admin)

        addAdminButton.setOnClickListener {
            val adminEmail = email.text.toString().trim()
            val adminPassword = password.text.toString().trim()

            if (adminEmail.isEmpty() || adminPassword.isEmpty()) {
                Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (adminPassword.length < 6) {
                Toast.makeText(this, "Password must be at least 6 characters!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(adminEmail, adminPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Admin Added Successfully!", Toast.LENGTH_SHORT).show()
                        finish() // Close activity after successful admin creation
                    } else {
                        val errorMessage = task.exception?.localizedMessage ?: "Failed to Add Admin!"
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
