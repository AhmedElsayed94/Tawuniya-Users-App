package com.ahmedelsayed94.tawuniyatask.data.repo

import com.ahmedelsayed94.tawuniyatask.data.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUsers(): List<User>
    fun getFavoriteUsers(): Flow<List<User>>
    suspend fun addToFavorites(user: User)
    suspend fun removeFromFavorites(user: User)
    suspend fun isFavoriteUser(userId: Int) : Boolean
}