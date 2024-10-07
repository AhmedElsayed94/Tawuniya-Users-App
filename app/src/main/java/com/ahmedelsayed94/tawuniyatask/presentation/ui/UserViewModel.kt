package com.ahmedelsayed94.tawuniyatask.presentation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedelsayed94.tawuniyatask.data.model.User
import com.ahmedelsayed94.tawuniyatask.domain.AddUserToFavoritesUseCase
import com.ahmedelsayed94.tawuniyatask.domain.GetFavoriteUsersUseCase
import com.ahmedelsayed94.tawuniyatask.domain.GetUsersUseCase
import com.ahmedelsayed94.tawuniyatask.domain.IsFavoriteUserUseCase
import com.ahmedelsayed94.tawuniyatask.domain.RemoveUserFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getFavoriteUsersUseCase: GetFavoriteUsersUseCase,
    private val addFavoriteUserUseCase: AddUserToFavoritesUseCase,
    private val removeFavoriteUserUseCase: RemoveUserFromFavoritesUseCase
) : ViewModel() {

    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList: StateFlow<List<User>> = _userList

    private val _favoriteUsers = getFavoriteUsersUseCase.invoke().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )
    val favoriteUsers: StateFlow<List<User>> = _favoriteUsers

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            val users = getUsersUseCase.invoke()
            _userList.value = users
        }
    }

    fun addFavoriteUser(user: User) {
        viewModelScope.launch {
            addFavoriteUserUseCase.invoke(user)
        }
    }

    fun removeFavoriteUser(user: User) {
        viewModelScope.launch {
            removeFavoriteUserUseCase.invoke(user)
        }
    }
}
