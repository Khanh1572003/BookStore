package com.example.bookstoredemo.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun AccountPage(){
    Column(modifier = Modifier.fillMaxSize()) {
        loginbarFun()
        Space()
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginbarFun(){
            Row(modifier = Modifier.fillMaxSize(),
                ) {
                TextButton(onClick = {},
                    modifier = Modifier.weight(1f)) {
                    Text(text = "Đăng Nhập",
                        fontSize = 20.sp,
                        color = Color.Red)
                }
                VerticalDivider(
                    modifier = Modifier.height(24.dp),
                    thickness = 1.dp
                )
                TextButton(onClick = {},
                    modifier = Modifier.weight(1f)) {
                    Text(text ="Đăng Ký",
                        fontSize = 20.sp,
                        color = Color.Red,
                        )
                }
            }
}
@Composable
fun Space(){
    Spacer(modifier = Modifier.height(5.dp))
}