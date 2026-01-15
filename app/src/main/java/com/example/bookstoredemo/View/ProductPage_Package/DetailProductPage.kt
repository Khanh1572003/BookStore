package com.example.bookstoredemo.View.ProductPage_Package

import android.view.Surface
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.GifBox
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookstoredemo.R
import com.example.bookstoredemo.ViewModel.SuggestViewModel
import com.example.bookstoredemo.ui.theme.Amber_Yellow
import com.example.bookstoredemo.ui.theme.LightRed
import java.nio.file.WatchEvent

@Preview(showBackground = true)
@Composable
fun DetailProductPage(){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
        ImageProduct()
        FlashSale()
        DetailProduct()
    }
}
@Composable
private fun ImageProduct(){

    Box(modifier = Modifier.fillMaxWidth().height(250.dp)){
        Image(
            painter = painterResource(R.drawable.anh31),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Box(modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
            contentAlignment = Alignment.BottomStart,
            ){
            Surface(modifier = Modifier
                .width(80.dp)
                .height(20.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color.Gray,
                contentColor = Color.White,
            ){
                Text(
                    text = "1/10",
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}
@Composable
private fun FlashSale(){
    val RedGradient = Brush.horizontalGradient(
        listOf(
            Color(0xFFba3030),
            Color(0xFFc72c2c),
            Color(0xFFce2525),
            Color(0xFFd62121),
            Color(0xFFdf1b1b),
        )
    )
    Row(modifier = Modifier.fillMaxWidth().background(RedGradient),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("FLASH SALE",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            fontStyle = FontStyle.Italic)
    }
}
@Composable
private fun DetailProduct(){
    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)) {
        //Text
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Bottom) {
            Text("00000",
                color = Color.Red,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp)
            Text("00000",
                color = Color.Gray,
                fontWeight = FontWeight.Medium,
                textDecoration = TextDecoration.LineThrough)
            Box(modifier = Modifier
                .width(40.dp)
                .height(20.dp)
                .background(LightRed),
                contentAlignment = Alignment.Center
            ){
                Text("-40%",
                    color = Color.Red,)
            }
        }
        //Text
        Row(modifier = Modifier.fillMaxWidth().clickable(onClick = {}),
            verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Chính sách khuyến mãi trên chỉ áp dụng tại BookStore_Online",
                    color = Color.Red,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    modifier = Modifier.width(340.dp))
            Icon(
                Icons.Default.ArrowForwardIos,
                contentDescription = null,
                modifier = Modifier.padding(8.dp)

            )
        }
        //TextBook
        Text(
            text = "Tên Sách",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            modifier = Modifier.wrapContentSize()
        )
        //Rating
        Row(modifier = Modifier.height(20.dp).padding(top = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)){
                Icon(
                    Icons.Default.Star,
                    contentDescription = null,
                    tint = Amber_Yellow
                )
                Text(text = "5.0",
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "(1)",
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.wrapContentWidth()
                )
            }
            VerticalDivider(thickness = 2.dp, color = Color.Gray)
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Đã bán"
                )
                Text(
                    text = "10k+",
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Divider(thickness = 2.dp, color = Color.LightGray)
        //Shipping
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.clickable(onClick = {}),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                Icon(
                    Icons.Default.LocalShipping,
                    contentDescription = null
                )
                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.Light)){
                            append("Dự kiến giao hàng")
                        }
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
                            append(" Chủ nhật - 16/11")
                        }
                    }
                )
            }
            Divider(thickness = 2.dp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(6.dp))
            //Pros
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.GifBox,
                    contentDescription = null
                )
                Text(
                    text = "Đổi trả miễn phí toàn quốc 30 ngày",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
@Composable
private fun SelectTypeItem(){

}
