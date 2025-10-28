package com.example.bookstoredemo.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstoredemo.ui.theme.BluePrimary
import com.example.bookstoredemo.ui.theme.orange

@Preview(showBackground = true)
@Composable
fun AccountPage(){
    Column(modifier = Modifier.fillMaxSize()) {
        loginbarFun()
        Space()
        loginmidFun()
        Space()
        registermidFun()
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun loginbarFun(){
            Row(modifier = Modifier.wrapContentSize(),
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
fun loginmidFun(){
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ){
        Text(text = "Email/ Số điện thoại", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = "",
            onValueChange = {}
        )
        Text(text = "Mật khẩu", fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = "",
            onValueChange = {},

        )
        TextButton(onClick = {},
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Quên mật khẩu?", color = Color.Red)
        }
        Button(onClick = {},
            modifier = Modifier.
            align(Alignment.CenterHorizontally)
                .width(320.dp)
            ) {
            Text(text = "Đăng Nhập")
        }
        HorizontalDivider(
            thickness = 2.dp
        )
        Button(onClick = {},
            modifier = Modifier.
            align(Alignment.CenterHorizontally)
                .width(320.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BluePrimary
            )
        ) {
            Text(text = "Đăng nhập bằng FaceBook", fontWeight = FontWeight.Bold)
        }
        Button(onClick = {},
            modifier = Modifier.
            align(Alignment.CenterHorizontally)
                .width(320.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = orange
            )
            ) {
            Text(text = "Đăng nhập bằng Gmail", fontWeight = FontWeight.Bold)
            Icon(imageVector = Icons.Default.Home, contentDescription = null, tint = Color.Gray)
        }
    }
}
@Composable
fun registermidFun(){
    Row(modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = "Bạn chưa có tài khoản?")
        TextButton(onClick = {}) {
            Text(text = "Đăng ký tài khoản", color = orange)
        }
    }
}

@Composable
fun Space(){
    Spacer(modifier = Modifier.height(5.dp))
}