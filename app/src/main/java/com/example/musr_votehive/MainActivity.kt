package com.example.musr_votehive

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Make sure activity_main.xml exists

        // Redirect to LoginActivity and finish MainActivity
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish() // Close MainActivity so it doesn’t stay in the back stack
    }
}



