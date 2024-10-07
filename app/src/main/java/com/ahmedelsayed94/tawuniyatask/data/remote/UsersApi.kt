package com.ahmedelsayed94.tawuniyatask.data.remote

import com.ahmedelsayed94.tawuniyatask.data.model.User
import retrofit2.http.GET

interface UsersApi {
    @GET("/users")
    suspend fun getUsers(): List<User>
}