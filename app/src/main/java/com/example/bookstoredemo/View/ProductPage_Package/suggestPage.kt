package com.example.bookstoredemo.View.ProductPage_Package

import android.icu.text.DecimalFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.bookstoredemo.Helper.formatCurrency
import com.example.bookstoredemo.Model.Product
import com.example.bookstoredemo.ViewModel.ProductStateUi
import com.example.bookstoredemo.ViewModel.SuggestViewModel
@Composable
fun SuggestPage(suggestViewModel: SuggestViewModel = viewModel()){
    val state by suggestViewModel.suggestState.collectAsState()
    SuggestPageContent(state)
}

@Composable
private fun SuggestPageContent(state: ProductStateUi){
    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.errorMessage != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Lỗi: ${state.errorMessage}")
            }
        }

        else -> {
                LazyVerticalGrid(modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 8.dp),
                    columns = GridCells.Fixed(2)) {
                    items(state.product){product->
                        ProductItem(product)
                    }
                }

            }
        }
    }
@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(340.dp).clickable(onClick = {})
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (product.url.isNotEmpty()) {
                AsyncImage(
                    model = product.url,
                    contentDescription = product.ten_san_pham,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
            Text(
                text = product.ten_san_pham,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.width(150.dp).height(50.dp),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (product.giam_gia > 0) {
                        Text(
                            text = formatCurrency(product.don_gia),
                            style = MaterialTheme.typography.bodyMedium,
                            textDecoration = TextDecoration.LineThrough,
                            color = Color.Gray
                        )
                        Surface(
                            color = Color.Red,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "-${product.giam_gia}%",
                                modifier = Modifier.padding(horizontal = 6.dp),
                                style = MaterialTheme.typography.labelSmall,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    } else {
                        Text(
                            text = formatCurrency(product.don_gia),
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Red,
                            fontWeight = FontWeight.Bold

                        )
                    }
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    val finalPrice = product.don_gia * (100 - product.giam_gia) / 100
                    if (product.giam_gia > 0)
                        Text(
                            text = formatCurrency(finalPrice),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red
                        )
                    Text(
                        text = "Đã bán: ${product.da_ban}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
