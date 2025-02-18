package com.example.musr_votehive

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AdminDashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        // Set up click listeners for the buttons
        findViewById<Button>(R.id.btn_add_candidate).setOnClickListener {
            startActivity(Intent(this, AddCandidateActivity::class.java))
        }

        findViewById<Button>(R.id.btn_add_admin).setOnClickListener {
            startActivity(Intent(this, AddAdminActivity::class.java))
        }

        findViewById<Button>(R.id.btn_change_password).setOnClickListener {
            startActivity(Intent(this, ChangeAdminPasswordActivity::class.java))
        }

        findViewById<Button>(R.id.btn_logout).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish() // Close the current activity
        }
    }
}
