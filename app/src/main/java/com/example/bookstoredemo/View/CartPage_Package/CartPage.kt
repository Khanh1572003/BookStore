package com.example.bookstoredemo.View.CartPage_Package


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstoredemo.R
import com.example.bookstoredemo.ui.theme.orange

@Preview(showBackground = true)
@Composable
fun CartScreen(){
    Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
    ) {
        EmptyCart()
    }
}
@Composable
fun EmptyCart(){
    val OrangeBrush = Brush.horizontalGradient(
        listOf(
            Color(0xFFFDB777),
            Color(0xFFFF6200),
            Color(0xFFFF6200),
            Color(0xFFFD7F2C),
            Color(0xFFFD9346),
            Color(0xFFFDA766),

        )
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), // Thêm padding để tránh sát mép
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.empty_cart),
            contentDescription = "Empty_cart",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .border(
                    BorderStroke(width = 2.dp, orange),
                    CircleShape
                )
                .padding(4.dp)
                .clip(CircleShape)
        )

        Text(
            text = "Chưa có sản phẩm trong giỏ hàng của bạn",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(horizontal = 8.dp)
        )

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
            contentPadding = PaddingValues(),
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .background(OrangeBrush, shape = RoundedCornerShape(25.dp)),
        ) {
            Text(
                text = "Mua sắm ngay",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth() // Dùng fillMaxWidth thay vì width cố định
            )
        }
    }
}
@Composable
fun itemScreen(){

}