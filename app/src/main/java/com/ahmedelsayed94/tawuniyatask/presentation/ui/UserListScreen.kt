package com.ahmedelsayed94.tawuniyatask.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmedelsayed94.tawuniyatask.data.model.User

@Composable
fun UserListScreen(viewModel: UserViewModel = hiltViewModel()) {
    val userList by viewModel.userList.collectAsState()
    val favoriteUsers by viewModel.favoriteUsers.collectAsState()

    LazyColumn {
        items(userList) { user ->
            UserItem(
                user = user,
                isFavorite = favoriteUsers.contains(user),
                onFavoriteClick = { isFavorite ->
                    if (isFavorite) {
                        viewModel.removeFavoriteUser(user)
                    } else {
                        viewModel.addFavoriteUser(user)
                    }
                }
            )
        }
    }
}

@Composable
fun UserItem(user: User, isFavorite: Boolean, onFavoriteClick: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = user.name, fontSize = 18.sp)
            Text(text = user.phone, fontSize = 14.sp)
            Text(text = user.email, fontSize = 14.sp)
        }
        IconButton(onClick = { onFavoriteClick(isFavorite) }) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = if (isFavorite) Color.Red else Color.Gray
            )
        }
    }
}
