@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jetweatherforecast.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AppBar(
    title: String,
    icon: ImageVector? = null,
    showNavigationIcon: Boolean = false,
    elevation: Dp = 0.dp,
    navController: NavController,
    actions: @Composable () -> Unit = {},
    onAddActionClick: () -> Unit = {},
    onNavigationIconClick: () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.shadow(
            elevation = elevation,
            spotColor = Color.DarkGray
        ),
        title = {
            Text(
                title,
                color = MaterialTheme.colorScheme.secondary,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
            )
        },
        navigationIcon = {
            if (showNavigationIcon) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "menu items",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.clickable {
                        onNavigationIconClick.invoke()
                    }
                )
            }
        },
        actions = { actions() },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = Color.White
        ),
    )
}