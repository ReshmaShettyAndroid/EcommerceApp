package com.example.ecommerceapp.presentation.view.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.ui.theme.primaryContainerLight

@Composable
fun PhoneScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(primaryContainerLight),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "PHONE SCREEN",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
    }
}