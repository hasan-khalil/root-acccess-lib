package com.example.firepassword.data.model

data class User(
    val uid: String = "",
    val username: String = "",
    val email: String = "",
    val createdAt: Long = System.currentTimeMillis()
)