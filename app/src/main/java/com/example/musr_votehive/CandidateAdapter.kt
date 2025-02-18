package com.example.musr_votehive

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class CandidateAdapter(private val candidates: List<Candidate>, private val context: Context) :
    RecyclerView.Adapter<CandidateAdapter.CandidateViewHolder>() {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("VotePrefs", Context.MODE_PRIVATE)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.candidate_item, parent, false)
        return CandidateViewHolder(view)
    }

    override fun onBindViewHolder(holder: CandidateViewHolder, position: Int) {
        val candidate = candidates[position]
        holder.name.text = "Candidate Full Name: ${candidate.fullName}"
        holder.id.text = "Candidate ID: ${candidate.id}"
        holder.department.text = "Department: ${candidate.department}"
        holder.manifesto.text = "Manifesto: ${candidate.manifesto}"
        holder.votes.text = "Votes: ${candidate.votes}"

        val votedCandidateId = sharedPreferences.getString("voted_candidate", null)

        if (votedCandidateId == candidate.id) {
            holder.voteButton.visibility = View.GONE
            holder.unvoteButton.visibility = View.VISIBLE
        } else {
            holder.voteButton.visibility = View.VISIBLE
            holder.unvoteButton.visibility = View.GONE
        }

        holder.voteButton.setOnClickListener {
            sharedPreferences.edit().putString("voted_candidate", candidate.id).apply()
            val databaseRef = FirebaseDatabase.getInstance().getReference("candidates").child(candidate.id)

            // Increase votes in Firebase and update UI
            databaseRef.child("votes").setValue(candidate.votes + 1).addOnSuccessListener {
                holder.votes.text = "Votes: ${candidate.votes + 1}"
                holder.voteButton.visibility = View.GONE
                holder.unvoteButton.visibility = View.VISIBLE
            }
        }

        holder.unvoteButton.setOnClickListener {
            sharedPreferences.edit().remove("voted_candidate").apply()
            if (candidate.votes > 0) {
                val databaseRef = FirebaseDatabase.getInstance().getReference("candidates").child(candidate.id)

                // Decrease votes in Firebase and update UI
                databaseRef.child("votes").setValue(candidate.votes - 1).addOnSuccessListener {
                    holder.votes.text = "Votes: ${candidate.votes - 1}"
                    holder.voteButton.visibility = View.VISIBLE
                    holder.unvoteButton.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemCount(): Int = candidates.size

    class CandidateViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.candidate_name)
        val id: TextView = view.findViewById(R.id.candidate_id)
        val department: TextView = view.findViewById(R.id.candidate_department)
        val manifesto: TextView = view.findViewById(R.id.candidate_manifesto)
        val votes: TextView = view.findViewById(R.id.vote_count)
        val voteButton: Button = view.findViewById(R.id.btn_vote)
        val unvoteButton: Button = view.findViewById(R.id.btn_unvote)
    }
}





