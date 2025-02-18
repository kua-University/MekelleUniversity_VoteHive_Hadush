package com.example.musr_votehive

//package com.example.musr_election

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AdminLoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        val email = findViewById<EditText>(R.id.admin_email)
        val password = findViewById<EditText>(R.id.admin_password)
        val loginBtn = findViewById<Button>(R.id.btn_admin_login)

        loginBtn.setOnClickListener {
            if (email.text.toString() == "admin@example.com" && password.text.toString() == "admin123") {
                startActivity(Intent(this, AdminDashboardActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid Admin Credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
