package com.ahmedelsayed94.tawuniyatask.domain

import com.ahmedelsayed94.tawuniyatask.data.repo.UsersRepository
import javax.inject.Inject

class IsFavoriteUserUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    suspend operator fun invoke(userId: Int): Boolean {
        return repository.isFavoriteUser(userId)
    }
}
