@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.jetweatherforecast.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetweatherforecast.ui.navigation.MenuItemEnum
import com.example.jetweatherforecast.ui.navigation.ViewEnum

@Composable
fun AppBar(
    title: String,
    navController: NavController,
    icon: @Composable () -> Unit = {},
    showNavigationIcon: Boolean = true,
    elevation: Dp = 0.dp,
    actions: @Composable () -> Unit = {}
) {
    TopAppBar(
        modifier = Modifier.shadow(
            elevation = elevation,
            spotColor = Color.DarkGray
        ),
        title = {
            Text(
                title,
                modifier = Modifier.padding(start = 16.dp),
                color = MaterialTheme.colorScheme.secondary,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
            )
        },
        navigationIcon = {
            if (showNavigationIcon) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .clickable {
                            navController.popBackStack()
                        }
                )
            } else {
                icon()
            }
        },
        actions = { actions() },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            navigationIconContentColor = Color.White
        ),
    )
}

@Composable
fun ShowActionsMenu(showActionsMenu: MutableState<Boolean>, navController: NavController) {
    var expanded by remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                showActionsMenu.value = false
            },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)
        ) {
            enumValues<MenuItemEnum>().forEach { menuItem ->
                DropdownMenuItem(text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = menuItem.icon,
                            contentDescription = menuItem.text,
                            tint = Color.LightGray
                        )
                        Text(text = menuItem.text, modifier = Modifier.padding(8.dp))
                    }
                }, onClick = {
                    expanded = false
                    showActionsMenu.value = false
                    navController.navigate(
                        when (menuItem) {
                            MenuItemEnum.ABOUT -> ViewEnum.ABOUT.name
                            MenuItemEnum.FAVORITE -> ViewEnum.FAVORITES.name
                            MenuItemEnum.SETTINGS -> ViewEnum.SETTINGS.name
                        }
                    )
                })
            }
        }
    }
}
