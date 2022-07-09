package com.example.proxima.Presentation.Screens

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.proxima.navigation.BottomBarScreen
import com.example.proxima.navigation.BottomNavGraph

@Composable
fun MainScreen(navController: NavHostController)
{

    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavBar(navController = navController)}) {

        BottomNavGraph(navController = navController)
    }
}

@Composable
fun BottomNavBar(navController: NavHostController)
{
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.LikedPosts,
        BottomBarScreen.YourPosts,
        BottomBarScreen.Profile

    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination=navBackStackEntry?.destination
    BottomNavigation() {
        screens.forEach{screen->

            AddItem(screen = screen, currentDestination = currentDestination, navController =navController )

        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,currentDestination: NavDestination?,
    navController: NavHostController
)
{

    BottomNavigationItem(label = { Text(text = screen.title) }, icon = { Icon(
        imageVector = screen.icon,
        contentDescription = "Navigation Icon"
    )}

    ,
    selected = currentDestination?.hierarchy?.any{ it.route == screen.route } ==true,
        onClick = {
            navController.navigate(screen.route)
        }
    )
    
    }

