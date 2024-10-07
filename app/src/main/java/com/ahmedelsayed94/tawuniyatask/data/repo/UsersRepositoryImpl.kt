package com.ahmedelsayed94.tawuniyatask.data.repo

import com.ahmedelsayed94.tawuniyatask.data.local.db.UserDao
import com.ahmedelsayed94.tawuniyatask.data.model.User
import com.ahmedelsayed94.tawuniyatask.data.remote.UsersApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val remoteDataSource: UsersApi,
    private val localDataSource: UserDao
) : UsersRepository{

    override suspend fun getUsers(): List<User> = remoteDataSource.getUsers()

    override fun getFavoriteUsers(): Flow<List<User>> = localDataSource.getAllFavoriteUsers()

    override suspend fun addToFavorites(user: User) = localDataSource.insertUser(user)

    override suspend fun removeFromFavorites(user: User) = localDataSource.deleteUser(user)

    override suspend fun isFavoriteUser(userId: Int) = localDataSource.isFavorite(userId)
}