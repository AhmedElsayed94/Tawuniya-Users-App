package com.ahmedelsayed94.tawuniyatask.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmedelsayed94.tawuniyatask.data.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}