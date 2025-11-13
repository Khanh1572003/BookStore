package com.example.bookstoredemo.View.AccountPage_Package

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sms
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookstoredemo.R
import com.example.bookstoredemo.ui.theme.orange

@Preview(showBackground = true)
@Composable
fun RegisScreen(){
    Column(modifier = Modifier.fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        regisForm()
    }
}
@Composable
private fun regisForm(){
    var phoneNum by remember {
        mutableStateOf("")
    }
    var passNum by remember {
        mutableStateOf("")
    }
    var showPass by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PhoneField(value = phoneNum, onValueChange = {phoneNum = it})
        PassField(value = passNum,
                  onValueChange = {passNum = it},
                  showPass = showPass,
                  onToggle = {showPass = !showPass}
                )
        selectSMSs()
        RegisButton(
            onClick = { },
            enabled = phoneNum.isNotEmpty() && passNum.isNotEmpty()
        )
        bottomPromt()
    }
}
@Composable
private fun PhoneField(value:String, onValueChange: (String) -> Unit){
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = "Số điện thoại",
            fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = "Nhập số điện thoại")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
    }
}
@Composable
private fun PassField(value: String,
              onValueChange: (String) -> Unit,
              showPass: Boolean,
              onToggle:()-> Unit){
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = "Mật khẩu",
            fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(text = "Nhập mật khẩu")
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if(showPass)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = onToggle) {
                    Icon(
                        imageVector = if(showPass)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = if (showPass)
                            "Ẩn mật khẩu"
                        else
                            "Hiện mật khẩu"
                    )
                }
            }
        )
    }
}
@Composable
private fun RegisButton(
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text("Đăng Ký", fontWeight = FontWeight.Bold)
    }
}
@Composable
private fun selectSMSs(){
    var selectedOption by remember { mutableStateOf<String?>(null) }
    Text(text = "Chọn phương thức xác minh OTP")
    Row (modifier = Modifier.padding(4.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween) {
            selectSMS(
                text = "Tin nhắn SMS",
                icon = rememberVectorPainter(Icons.Default.Sms),
                isSelected = selectedOption=="SMS",
                onClick ={ selectedOption="SMS" }
            )
            selectSMS(
                text = "Zalo SMS",
                icon = painterResource(R.drawable.icons_zalo),
                isSelected = selectedOption == "Zalo",
                onClick ={ selectedOption = "Zalo"}
            )
    }
}
@Composable
private fun selectSMS(text:String,
              icon: Painter,
              onClick: () -> Unit,
              isSelected: Boolean
){
    val borderState by animateColorAsState(
        targetValue = if(isSelected) Color.Blue else Color.Gray,
        label = "border"
    )
    Button(onClick = onClick,
        modifier = Modifier.wrapContentSize(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.Gray,
        ),
        border = BorderStroke(width = if
                (isSelected) 2.dp else 1.dp,
                color = borderState)
        ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = Color.Unspecified,
            modifier =Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
fun bottomPromt(){
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center)
            {
            Text(text = "Bạn đã có tài khoản?")
            TextButton(onClick = {}) {
                Text(text = "Đăng nhập ngay", color = orange)
            }
        }
    Column(modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Với việc đăng ký, bạn đã đồng ý với BookStore về ")
        Text(text = "Điều khoản & chính sách sử dụng", color = Color.Blue)
    }
}