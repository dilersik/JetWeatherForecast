package com.example.jetweatherforecast.ui.views.favorites

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetweatherforecast.model.local.Favorite
import com.example.jetweatherforecast.ui.navigation.ViewEnum
import com.example.jetweatherforecast.ui.theme.Purple40
import com.example.jetweatherforecast.ui.theme.Purple80
import com.example.jetweatherforecast.ui.widgets.AppBar

@Composable
fun FavoritesView(navController: NavController) {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val list = viewModel.favorites.collectAsState().value

    Scaffold(topBar = {
        AppBar(title = "Favorite Cities", navController = navController)
    }) { padding ->
        Surface(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn {
                    items(items = list) {
                        CityRow(it, navController, viewModel)
                    }
                }
            }
        }
    }
}

@Composable
private fun CityRow(
    favorite: Favorite,
    navController: NavController,
    viewModel: FavoritesViewModel
) {
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                navController.navigate(ViewEnum.MAIN.name + "/${favorite.city}")
            },
        shape = CircleShape.copy(topEnd = CornerSize(6.dp)),
        color = Purple80
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = favorite.city,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(.5f)
            )
            Text(
                text = favorite.country,
                style = MaterialTheme.typography.titleMedium,
                color = Purple40,
                modifier = Modifier.weight(.3f)
            )
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "delete",
                modifier = Modifier
                    .weight(.1f)
                    .clickable { viewModel.remove(favorite) },
                tint = Color.Red.copy(alpha = 0.5f)
            )
        }
    }
}
