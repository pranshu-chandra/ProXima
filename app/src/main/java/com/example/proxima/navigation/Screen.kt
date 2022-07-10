package com.example.proxima.navigation

sealed class Screen(val route:String)
{
object Login:Screen(route ="login")
object MainScreen:Screen(route ="main")
object Register:Screen(route ="register")
}
