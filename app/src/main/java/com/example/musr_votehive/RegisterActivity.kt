package com.example.musr_votehive

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    // Declare views
    private lateinit var editTextUsername: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword: EditText
    private lateinit var buttonRegister: Button

    // Firebase Authentication
    private lateinit var auth: FirebaseAuth

    // Firebase Realtime Database
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register) // Set the layout file

        // Initialize Firebase Auth and Database
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference

        // Initialize views
        editTextUsername = findViewById(R.id.username)
        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        editTextConfirmPassword = findViewById(R.id.confirm_password)
        buttonRegister = findViewById(R.id.btn_register)

        // Set click listener for the register button
        buttonRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        // Get input values
        val username = editTextUsername.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        val confirmPassword = editTextConfirmPassword.text.toString().trim()

        // Validate inputs
        if (username.isEmpty()) {
            editTextUsername.error = "Username is required"
            editTextUsername.requestFocus()
            return
        }

        if (email.isEmpty()) {
            editTextEmail.error = "Email is required"
            editTextEmail.requestFocus()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.error = "Enter a valid email address"
            editTextEmail.requestFocus()
            return
        }

        if (password.isEmpty()) {
            editTextPassword.error = "Password is required"
            editTextPassword.requestFocus()
            return
        }

        if (password.length < 6) {
            editTextPassword.error = "Password must be at least 6 characters"
            editTextPassword.requestFocus()
            return
        }

        if (confirmPassword.isEmpty()) {
            editTextConfirmPassword.error = "Confirm Password is required"
            editTextConfirmPassword.requestFocus()
            return
        }

        if (password != confirmPassword) {
            editTextConfirmPassword.error = "Passwords do not match"
            editTextConfirmPassword.requestFocus()
            return
        }

        // If all validations pass, proceed with Firebase registration
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Registration successful, save user data to Realtime Database
                    val user = auth.currentUser
                    user?.let {
                        val userId = it.uid
                        saveUserToDatabase(userId, username, email)
                    }
                } else {
                    // Registration failed, display error message
                    val errorMessage = task.exception?.message
                    Toast.makeText(this, "Registration failed: $errorMessage", Toast.LENGTH_LONG).show()
                    Log.e("RegisterError", "Registration failed: $errorMessage")
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("RegisterError", "Error: ${e.message}")
            }
    }

    private fun saveUserToDatabase(userId: String, username: String, email: String) {
        // Ensure Firebase Realtime Database rules allow write access
        val user = mapOf(
            "username" to username,
            "email" to email
        )

        database.child("users").child(userId).setValue(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Data saved successfully
                    Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
                } else {
                    // Data saving failed
                    val errorMessage = task.exception?.message
                    Toast.makeText(this, "Failed to save user data: $errorMessage", Toast.LENGTH_LONG).show()
                    Log.e("DatabaseError", "Failed to save user data: $errorMessage")
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Database Error: ${e.message}", Toast.LENGTH_LONG).show()
                Log.e("DatabaseError", "Database Error: ${e.message}")
            }
    }
}

