package com.ahmedelsayed94.tawuniyatask.di

import android.app.Application
import androidx.room.Room
import com.ahmedelsayed94.tawuniyatask.data.local.db.UserDao
import com.ahmedelsayed94.tawuniyatask.data.local.db.UsersDatabase
import com.ahmedelsayed94.tawuniyatask.data.remote.UsersApi
import com.ahmedelsayed94.tawuniyatask.data.repo.UsersRepository
import com.ahmedelsayed94.tawuniyatask.data.repo.UsersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService(): UsersApi {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UsersApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(app: Application): UsersDatabase {
        return Room.databaseBuilder(app, UsersDatabase::class.java, "app_db").build()
    }

    @Provides
    fun provideUserDao(db: UsersDatabase): UserDao = db.userDao()

    @Provides
    fun provideUserRepository(
        remoteDataSource: UsersApi,
        localDataSource: UserDao
    ): UsersRepository {
        return UsersRepositoryImpl(remoteDataSource, localDataSource)
    }
}
