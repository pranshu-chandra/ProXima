package com.example.proxima.Presentation.Screens.LikedPosts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp


@Composable
fun LikedPostsPage() {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Magenta)) {
            Text(text = "Liked", modifier = Modifier.align(Alignment.Center),
                fontSize = 50.sp, color = Color.DarkGray)

        }





}


@Composable
@Preview(showBackground = true)
fun Preview() {
    LikedPostsPage()
}