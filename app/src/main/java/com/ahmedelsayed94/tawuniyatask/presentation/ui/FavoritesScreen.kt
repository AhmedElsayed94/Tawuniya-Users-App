package com.ahmedelsayed94.tawuniyatask.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ahmedelsayed94.tawuniyatask.R
import com.ahmedelsayed94.tawuniyatask.data.model.User

@Composable
fun FavoritesScreen(viewModel: UserViewModel = hiltViewModel()) {
    val favoriteUsers by viewModel.favoriteUsers.collectAsState()
    Column {
        if (favoriteUsers.isEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.no_favorites_added), fontSize = 18.sp)
            }
        }
        else {
            LazyColumn {
                items(favoriteUsers) { user ->
                    FavoriteUserItem(
                        user = user,
                        onRemoveClick = { viewModel.removeFavoriteUser(it) })
                }
            }
        }
    }
}

@Composable
fun FavoriteUserItem(user: User, onRemoveClick: (User) -> Unit) {
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
        IconButton(onClick = { onRemoveClick(user) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                tint = Color.Red
            )
        }
    }
}

