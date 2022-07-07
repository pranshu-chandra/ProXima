package com.example.proxima.navigation

sealed class Screen(val route:String)
{
object Login:Screen(route ="login")

}
