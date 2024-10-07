package com.ahmedelsayed94.tawuniyatask.domain

import com.ahmedelsayed94.tawuniyatask.data.model.User
import com.ahmedelsayed94.tawuniyatask.data.repo.UsersRepository
import javax.inject.Inject

class AddUserToFavoritesUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    suspend operator fun invoke(user: User) {
        repository.addToFavorites(user)
    }
}