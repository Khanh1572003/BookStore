package com.example.bookstoredemo.View.UserPage_Package

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AllInbox
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.LocalShipping
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstoredemo.R

@Preview(showBackground = true)
@Composable
fun userPage(){
    val state = rememberScrollState()
    Column(modifier = Modifier
        .verticalScroll(state)
        .fillMaxSize()
        .background(color = Color.LightGray),
        verticalArrangement = Arrangement.spacedBy(5.dp)

    ) {
        headuserPage()
        OrderStatus()
        UserCategory()
    }

}
@Composable
private fun headuserPage() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Header: Tên + Avatar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // User Info
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Khưu Khải Khanh",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = "Thành viên Bạc",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("B-Point ")
                            }
                            withStyle(style = SpanStyle(color = Color.Gray)) {
                                append("tích luỹ 0")
                            }
                        }
                    )
                }

                // Avatar
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(color = Color.Gray),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "✓",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Progress Text
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append("Tích luỹ thêm ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("30.000 B-Point ")
                    }
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append("để nâng hạng ")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append("Vàng")
                    }
                },
                fontSize = 13.sp
            )

            // Divider
            Divider(thickness = 1.dp, color = Color.LightGray)

            // B-Point & Freeship Stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // B-Point
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "B-Point hiện có",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(color = Color.Gray),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "B", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Text(
                            text = "0",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // Vertical Divider
                VerticalDivider(
                    thickness = 1.dp,
                    color = Color.LightGray,
                    modifier = Modifier.height(50.dp)
                )

                // Freeship
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Freeship hiện có",
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape)
                                .background(color = Color.Gray),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "0", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                        Text(
                            text = "lần",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
@Composable
private fun OrderStatus(){
    Column(modifier = Modifier.fillMaxWidth()
        .background(color = Color.White)
        .padding(8.dp),
        ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
           Text(text = "Đơn hàng của tôi",
               fontSize = 16.sp,
               fontWeight = FontWeight.Medium
               )
            Image(imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
                )
        }
        Row(modifier = Modifier.fillMaxWidth().padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            OrderStatusItem(
                icon = Icons.Default.CreditCard,
                label = "Chờ thanh toán",
                onClick = {}
            )
            OrderStatusItem(
                icon = Icons.Default.AllInbox,
                label = "Đang xử lý",
                onClick = {}
            )
            OrderStatusItem(
                icon = Icons.Default.LocalShipping,
                label = "Đang giao hàng",
                onClick = {}
            )
            OrderStatusItem(
                icon = Icons.Default.Check,
                label = "Hoàn tất",
                onClick = {}
            )
        }
    }
}
@Composable
private fun OrderStatusItem(icon: ImageVector,
                            label:String,
                            onClick:()-> Unit){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.clickable(onClick=onClick)) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ){
            Image(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
        }
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier.width(70.dp)
        )
    }
}
@Composable
private fun UserCategory(){
    Column(modifier = Modifier.fillMaxWidth()
        .background(Color.White),
        ) {
        Column (modifier = Modifier.fillMaxWidth().padding(4.dp)) {
            UserCategoryStatus(
                label = "Ví voucher",
                icon = R.drawable.voucher_discount_black_icon,
                onClick ={}
            )
            UserCategoryStatus(
                label = "Tài khoản F-Point",
                icon = R.drawable.evaluation,
                onClick ={}
            )
            UserCategoryStatus(
                label = "Hoạt động B-Game",
                icon = R.drawable.console,
                onClick ={}
            )
            UserCategoryStatus(
                label = "Sản phẩm yêu thích",
                icon = R.drawable.heart,
                onClick ={}
            )
            UserCategoryStatus(
                label = "Sách theo bộ",
                icon = R.drawable.book,
                onClick ={}
            )
            UserCategoryStatus(
                label = "Hồ sơ cá nhân",
                icon = R.drawable.user,
                onClick ={}
            )
            UserCategoryStatus(
                label = "Trung tâm trợ giúp",
                icon = R.drawable.attention,
                onClick ={}
            )
        }

    }
}
@Composable
private fun UserCategoryStatus(label:String,
                               @DrawableRes icon: Int,
                               onClick: () -> Unit)
{
    Column(modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(modifier = Modifier.clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = label,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(start = 6.dp)
            )
        }
        Divider(modifier = Modifier,1.dp,Color.LightGray)
    }
}
