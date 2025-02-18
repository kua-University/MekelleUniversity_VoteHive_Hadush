package com.example.musr_votehive

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class StudentDashboardActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var candidateList: ArrayList<Candidate>
    private lateinit var adapter: CandidateAdapter
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dashboard)
        Log.d("StudentDashboard", "Activity created")

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        candidateList = ArrayList()
        adapter = CandidateAdapter(candidateList, this)
        recyclerView.adapter = adapter

        database = FirebaseDatabase.getInstance().getReference("candidates")

        // Listen for changes in the "candidates" node
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                Log.d("StudentDashboard", "Data fetched from Firebase")
                candidateList.clear()
                for (data in snapshot.children) {
                    val candidate = data.getValue(Candidate::class.java)
                    if (candidate != null && candidate.fullName.isNotEmpty() && candidate.department.isNotEmpty()) {
                        candidateList.add(candidate)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("StudentDashboard", "Firebase error: ${error.message}")
            }
        })


        findViewById<Button>(R.id.btn_logout).setOnClickListener {
            Log.d("StudentDashboard", "Logout button clicked")
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}








