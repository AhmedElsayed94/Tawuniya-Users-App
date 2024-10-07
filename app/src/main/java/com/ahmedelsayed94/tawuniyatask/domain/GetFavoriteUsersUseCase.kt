package com.ahmedelsayed94.tawuniyatask.domain

import com.ahmedelsayed94.tawuniyatask.data.model.User
import com.ahmedelsayed94.tawuniyatask.data.repo.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteUsersUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    operator fun invoke(): Flow<List<User>> {
        return repository.getFavoriteUsers()
    }
}