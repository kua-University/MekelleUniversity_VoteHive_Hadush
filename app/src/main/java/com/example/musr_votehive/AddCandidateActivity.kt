package com.example.musr_votehive
//package com.example.musr_election

//import Candidate
import com.example.musr_votehive.Candidate

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddCandidateActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_candidate)

        database = FirebaseDatabase.getInstance().getReference("candidates")

        val candidateName = findViewById<EditText>(R.id.candidate_name)
        val candidateId = findViewById<EditText>(R.id.candidate_id)
        val candidateDept = findViewById<EditText>(R.id.candidate_department)
        val candidateManifesto = findViewById<EditText>(R.id.candidate_manifesto)
        val addButton = findViewById<Button>(R.id.btn_add_candidate)

        addButton.setOnClickListener {
            val name = candidateName.text.toString()
            val id = candidateId.text.toString()
            val dept = candidateDept.text.toString()
            val manifesto = candidateManifesto.text.toString()

            if (name.isNotEmpty() && id.isNotEmpty() && dept.isNotEmpty() && manifesto.isNotEmpty()) {
                val candidate = Candidate(name, id, dept, manifesto)
                database.child(id).setValue(candidate).addOnSuccessListener {
                    Toast.makeText(this, "Candidate Added Successfully!", Toast.LENGTH_SHORT).show()
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed to Add Candidate!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Fill all fields!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
