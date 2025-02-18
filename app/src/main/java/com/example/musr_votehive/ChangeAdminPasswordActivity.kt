package com.example.musr_votehive

//package com.example.musr_election

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChangeAdminPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_admin_password)

        val currentPassword = findViewById<EditText>(R.id.current_password)
        val newPassword = findViewById<EditText>(R.id.new_password)
        val confirmPassword = findViewById<EditText>(R.id.confirm_password)
        val saveButton = findViewById<Button>(R.id.btn_save)

        saveButton.setOnClickListener {
            val current = currentPassword.text.toString()
            val newPass = newPassword.text.toString()
            val confirmPass = confirmPassword.text.toString()

            if (current == "admin123" && newPass.isNotEmpty() && newPass == confirmPass) {
                Toast.makeText(this, "Password Updated Successfully!", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Invalid Credentials or Mismatched Passwords", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
