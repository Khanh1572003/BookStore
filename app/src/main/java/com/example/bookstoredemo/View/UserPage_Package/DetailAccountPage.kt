@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.example.bookstoredemo.View.UserPage_Package

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.bookstoredemo.Model.UserProfile
import com.example.bookstoredemo.ui.theme.Red
import com.example.bookstoredemo.ui.theme.boldOrange
import kotlin.collections.get

// Preview riêng không có parameters
@Preview(showBackground = true)
@Composable
private fun UpdateInfoScreenPreview() {
    UpdateInfoScreen()
}

// Screen chính
@Composable
fun UpdateInfoScreen() {
    var userProfile by remember { mutableStateOf(UserProfile()) }
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val titleMap = mapOf(
        "update_info" to "Cập nhật thông tin",
        "email" to "Cập nhật email",
        "sdt" to "Cập nhật số điện thoại",
        "invoice" to "Cập nhật thông tin xuất hoá đơn",
        "address" to "Cập nhật địa chỉ giao hàng"
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        topBar = {
            TopBar(
                title = titleMap[currentRoute] ?: "Cập nhật thông tin",
                canNavBack = true,
                onNavigateBack = { navController.navigateUp() }
            )
        },
        bottomBar = {
            ButtonBar(
                onSaveClick = {
                    // TODO: Lưu thông tin
                    if (userProfile.isComplete()) {
                        // Save logic here
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            UpdatePrompt(
                userProfile = userProfile,
                onProfileChange = { userProfile = it }
            )
            AddessPromt()
        }
    }
}

@Composable
fun UpdatePrompt(
    userProfile: UserProfile,
    modifier: Modifier = Modifier,
    onProfileChange: (UserProfile) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)

    ) {
        Text(
            text = "Cập nhật thông tin để nhận được B-Point",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        InpurField(
            label = "Họ và tên",
            value = userProfile.fullName,
            placeholder = "Nhập họ và tên",
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = null)
            },
            onValueChange = { onProfileChange(userProfile.copy(fullName = it)) }
        )

        InpurField(
            label = "Email",
            value = userProfile.email,
            placeholder = "example@email.com",
            keyboardType = KeyboardType.Email,
            isError = userProfile.email.isNotBlank() && !userProfile.isValidEmail(),
            errorMessage = "Email không hợp lệ",
            leadingIcon = {
                Icon(Icons.Default.Email, contentDescription = null)
            },
            onValueChange = { onProfileChange(userProfile.copy(email = it)) }
        )

        InpurField(
            label = "Số điện thoại",
            value = userProfile.phone,
            placeholder = "0123456789",
            keyboardType = KeyboardType.Phone,
            isError = userProfile.phone.isNotBlank() && !userProfile.isValidPhone(),
            errorMessage = "Số điện thoại không hợp lệ",
            leadingIcon = {
                Icon(Icons.Default.Phone, contentDescription = null)
            },
            onValueChange = { onProfileChange(userProfile.copy(phone = it)) }
        )
        DatePickerField(
            label = "Ngày sinh",
            value = userProfile.dateofBirth,
            placeholder = "DD/MM/YYYY",
            onDateSelected = {
                onProfileChange(userProfile.copy(dateofBirth = it))
            }
        )
        GenderSelector(
            selectedGender = userProfile.gender,
            onGenderSelected = { onProfileChange(userProfile.copy(gender = it)) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    title: String,
    canNavBack: Boolean = false,
    onNavigateBack: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.wrapContentHeight(),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,

            )
        },
        navigationIcon = {
            if (canNavBack) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Red,
            titleContentColor = Color.White
        )
    )
}

@Composable
private fun ButtonBar(
    onSaveClick: () -> Unit = {}  // ← Thêm callback
) {
    BottomAppBar {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onSaveClick,  // ← Thêm logic
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier
                    .width(350.dp)
                    .background(color = boldOrange, RoundedCornerShape(16.dp))
            ) {
                Text(
                    "Lưu thay đổi",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Composable
private fun GenderSelector(
    label: String = "Giới tính",
    selectedGender: String,
    modifier: Modifier = Modifier,
    onGenderSelected: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Nam", "Nữ", "Khác").forEach { gender ->
                FilterChip(
                    selected = selectedGender == gender,
                    onClick = { onGenderSelected(gender) },
                    label = { Text(gender) }
                )
            }
        }
    }
}
@Composable
private fun AddessPromt(){
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp))
    {
        Text(
            text = "Sổ địa chỉ"
        )
        Button(onClick = {},
            colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White,)
                .border(1.dp,Color.Blue, shape = RoundedCornerShape(12.dp)),

            ) {
            Text("Thêm địa chỉ mới", color = Color.Blue)
        }
    }
}