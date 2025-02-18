package com.example.musr_votehive

data class Candidate(
    val id: String = "",
    val fullName: String = "",
    val department: String = "",
    val manifesto: String = "",
    var votes: Long = 0L // Ensure votes are stored as Long
)






