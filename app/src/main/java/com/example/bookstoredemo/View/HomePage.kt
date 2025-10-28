@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.bookstoredemo.View

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fitInside
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomePage(modifier: Modifier = Modifier){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val titleMap = mapOf(
        "home" to "Trang chủ",
        "account" to "Tài Khoản",
        "suggest" to "Đề xuất",
        "notification" to "Thông báo",
        "cart" to "Giỏ hàng"
    )
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(title = titleMap[currentRoute]?:"BookStore",
                canNavigateBack = false)
        },
        bottomBar = {
            BottomAppBar(navController = navController,
                currentRoute = currentRoute)
        },
        ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding
            )
        ){
            composable("home") {
                AccountPage()
            }
            composable("account") {

            }
            composable("suggest") {

            }
            composable("notification") {

            }
            composable("cart") {

            }
        }

    }
}
@Composable
fun BottomAppBar(
    navController: NavHostController,
    currentRoute:String?
)
{
    val navItemList = listOf(
        navItem("Home", Icons.Default.Home,"home"),
        navItem("Tài khoản", Icons.Default.AccountBox,"account"),
        navItem("Gợi ý", Icons.Default.Star,"suggest"),
        navItem("Thông báo", Icons.Default.Notifications,"notification"),
        navItem("Giỏ hàng", Icons.Default.ShoppingCart,"cart")
    )
        NavigationBar {
            navItemList.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route){
                            popUpTo(navController.graph.startDestinationId){
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState= true
                        }
                    },
                    icon = {Icon(imageVector = item.image, contentDescription = "Icon")},
                    label = {
                        Text(text = item.title)
                    }

                )
            }
        }
    }

@Composable
fun AppTopBar(title: String,
              canNavigateBack: Boolean= false,
              onNavigateBack:   ()->Unit = {}){
    CenterAlignedTopAppBar(
        modifier = Modifier.height(56.dp),
        title = {
            Text(text = title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
        containerColor = MaterialTheme.colorScheme.onPrimary,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
    )
}
data class navItem(
    val title: String,
    val image: ImageVector,
    val route: String,
)
