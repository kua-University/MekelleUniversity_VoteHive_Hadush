package com.example.musr_votehive

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()
        progressBar = findViewById(R.id.progressBar)

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val loginBtn = findViewById<Button>(R.id.btn_login)
        val registerText = findViewById<TextView>(R.id.txt_register)
        val adminLoginText = findViewById<TextView>(R.id.txt_admin_login)

        loginBtn.setOnClickListener {
            val userEmail = email.text.toString().trim()
            val userPassword = password.text.toString().trim()

            // Validate input
            if (userEmail.isEmpty() || userPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Show progress bar and disable login button to avoid multiple clicks
            progressBar.visibility = View.VISIBLE
            loginBtn.isEnabled = false

            // Firebase login
            auth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this) { task ->
                    progressBar.visibility = View.GONE
                    loginBtn.isEnabled = true

                    if (task.isSuccessful) {
                        // If login is successful, log the user in and navigate to the dashboard
                        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                        Log.d("LoginActivity", "User logged in successfully: ${auth.currentUser?.uid}")

                        // Check if the user is not null before navigating
                        if (auth.currentUser != null) {
                            Log.d("LoginActivity", "Navigating to StudentDashboardActivity")
                            startActivity(Intent(this, StudentDashboardActivity::class.java))
                            finish()  // Close the login activity so the user can't go back to it
                        } else {
                            Toast.makeText(this, "User not found!", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        // If login fails, show an error message
                        val errorMessage = when (task.exception) {
                            is FirebaseAuthInvalidUserException -> "User not found. Please register."
                            is FirebaseAuthInvalidCredentialsException -> "Invalid credentials. Please check your email and password."
                            else -> "Login failed. Please try again later."
                        }
                        Log.e("LoginError", "Error: ${task.exception?.message}")
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Register Activity
        registerText.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Admin Login Activity
        adminLoginText.setOnClickListener {
            startActivity(Intent(this, AdminLoginActivity::class.java))
        }
    }
}





