package com.example.bookstoredemo.View.HomePage_Package

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.bookstoredemo.R
import com.example.bookstoredemo.ViewModel.BannerUiState
import com.example.bookstoredemo.ViewModel.MainViewModel
import kotlinx.coroutines.delay


@Composable
fun HomePageDetail(viewModel: MainViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomePageDetailContent(uiState)
}

@Composable
private fun HomePageDetailContent(uiState: BannerUiState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopSearchBar()
        SliderBanner(uiState)
        HomeCategoryItems()
    }
}
@Composable
private fun SliderBanner(uiState: BannerUiState) {
    val actualSize = uiState.slider.size
    val pagerState = rememberPagerState(
        initialPage = Int.MAX_VALUE / 2,
        pageCount = { Int.MAX_VALUE }
    )

    // Tự động cuộn
    LaunchedEffect(actualSize) {
        if (actualSize == 0) return@LaunchedEffect

        while (true) {
            delay(3000)
            pagerState.animateScrollToPage(
                page = pagerState.currentPage + 1,
                animationSpec = tween(
                    durationMillis = 600,
                    easing = FastOutSlowInEasing
                )
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxSize(),
                pageSpacing = 0.dp
            ) { page ->
                // Lấy index thật từ virtual page
                val actualIndex = page % actualSize
                val banner = uiState.slider[actualIndex]

                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    AsyncImage(
                        model = banner.url,
                        contentDescription = "Banner ${banner.id}",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            // Indicator - Hiển thị vị trí thật
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp)
            ) {
                repeat(actualSize) { index ->
                    val isSelected = (pagerState.currentPage % actualSize) == index
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(if (isSelected) 10.dp else 6.dp)
                            .clip(CircleShape)
                            .background(
                                if (isSelected) Color.White
                                else Color.White.copy(alpha = 0.5f)
                            )
                    )
                }
            }
        }
    }
}

@Composable
private fun TopSearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon Category
        IconButton(
            onClick = { /* Xử lý sự kiện */ },
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Category,
                contentDescription = "Danh mục",
                tint = Color.DarkGray,
                modifier = Modifier.size(28.dp)
            )
        }

        // Search Box
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .height(44.dp)
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable { }
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Nhập sản phẩm tìm kiếm",
                color = Color.Gray,
                fontSize = 14.sp,
                maxLines = 1
            )
        }

        IconButton(
            onClick = { },
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.QrCode2,
                contentDescription = "Quét QR",
                tint = Color.DarkGray,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun HomeCategoryItems(){
    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Row() {
            HomeCategory(
                label = "11.11",
                image = R.drawable.june,
                onClick ={}
            )
            HomeCategory(
                label = "Gift Card",
                image = R.drawable.gift_card,
                onClick ={}
            )
            HomeCategory(
                label = "Điểm danh",
                image = R.drawable.mushroom,
                onClick ={}
            )
            HomeCategory(
                label = "SBooks",
                image = R.drawable.books,
                onClick ={}
            )
            HomeCategory(
                label = "Flash Sale",
                image = R.drawable.flash,
                onClick ={}
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Row() {
            HomeCategory(
                label = "Mã giảm giá",
                image = R.drawable.coupons,
                onClick ={}
            )
            HomeCategory(
                label = "Sản Phẩm Mới",
                image = R.drawable.new_item,
                onClick ={}
            )
            HomeCategory(
                label = "Phiên Chợ Đồ Cũ",
                image = R.drawable.store,
                onClick ={}
            )
            HomeCategory(
                label = "Ngoại Văn",
                image = R.drawable.planet_earth,
                onClick ={}
            )
            HomeCategory(
                label = "Manga",
                image = R.drawable.comic,
                onClick ={}
            )
        }
    }
}

@Composable
private fun HomeCategory(label: String,
                         @DrawableRes image: Int,
                         onClick:()-> Unit)
{
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier.size(36.dp)
        )
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            maxLines = 2,
            modifier = Modifier.width(70.dp),
            lineHeight = 13.sp
        )
    }
}

