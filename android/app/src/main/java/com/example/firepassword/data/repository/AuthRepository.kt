package com.example.firepassword.data.repository

import com.example.firepassword.utils.Resource

interface AuthRepository {

    suspend fun register(
        username: String,
        email: String,
        password: String
    ): Resource<Unit>

    suspend fun login(
        email: String,
        password: String
    ): Resource<Unit>

    fun logout()
}