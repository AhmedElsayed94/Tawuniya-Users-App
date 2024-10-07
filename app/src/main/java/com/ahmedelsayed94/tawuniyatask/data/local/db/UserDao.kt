package com.ahmedelsayed94.tawuniyatask.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ahmedelsayed94.tawuniyatask.data.model.User
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM user")
    fun getAllFavoriteUsers(): Flow<List<User>>

    @Query("SELECT EXISTS(SELECT 1 FROM User WHERE id = :userId)")
    suspend fun isFavorite(userId: Int): Boolean
}