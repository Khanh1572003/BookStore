package com.example.bookstoredemo.View.AccountPage_Package

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assistant
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookstoredemo.R
import com.example.bookstoredemo.ui.theme.BluePrimary
import com.example.bookstoredemo.ui.theme.orange
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle

@Preview(showBackground = true)
@Composable
fun LoginScreen() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).verticalScroll(scrollState,true),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        LoginForm()
        HorizontalDivider(thickness = 2.dp)
        SocialLoginButtons()
        RegisterPrompt()
        AssisstancePromt()
    }
}

// ============ FORM COMPONENTS ============
@Composable
private fun LoginForm() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        AccountField(
            value = email,
            onValueChange = { email = it }
        )

        PasswordField(
            value = password,
            onValueChange = { password = it },
            showPassword = showPassword,
            onTogglePassword = { showPassword = !showPassword }
        )

        ForgotPasswordButton()

        LoginButton(
            onClick = { /* Handle login */ },
            enabled = email.isNotEmpty() && password.isNotEmpty()
        )
    }
}

@Composable
private fun AccountField(
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = "Email/ Số điện thoại",
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text("Nhập email/ số điện thoại") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
    }
}

@Composable
private fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    showPassword: Boolean,
    onTogglePassword: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = "Mật khẩu",
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text("Nhập mật khẩu") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (showPassword)
                VisualTransformation.None
            else
                PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = onTogglePassword) {
                    Icon(
                        imageVector = if (showPassword)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff,
                        contentDescription = if (showPassword)
                            "Ẩn mật khẩu"
                        else
                            "Hiện mật khẩu"
                    )
                }
            },
            singleLine = true
        )
    }
}

@Composable
private fun ForgotPasswordButton() {
    TextButton(
        onClick = { /* Handle forgot password */ },
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text("Quên mật khẩu?", color = Color.Red)
        }
    }
}

@Composable
private fun LoginButton(
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
        Text("Đăng Nhập", fontWeight = FontWeight.Bold)
    }
}

// ============ SOCIAL LOGIN ============
@Composable
private fun SocialLoginButtons() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SocialLoginButton(
            text = "Đăng nhập bằng Facebook",
            icon = R.drawable.icons_facebook,
            backgroundColor = BluePrimary,
            onClick = { /* Handle Facebook login */ }
        )

        SocialLoginButton(
            text = "Đăng nhập bằng Gmail",
            icon = R.drawable.icons_google,
            backgroundColor = orange,
            onClick = { /* Handle Google login */ }
        )
    }
}

@Composable
private fun SocialLoginButton(
    text: String,
    @DrawableRes icon: Int,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        )
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Bold
        )
    }
}

// ============ REGISTER PROMPT ============
@Composable
private fun RegisterPrompt() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Bạn chưa có tài khoản?",
            color = Color.Black
        )
        TextButton(
            onClick = {},
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            Text(
                text = "Đăng ký tài khoản",
                color = orange
            )
        }
    }

}
//=============ASSISTANCE PROMPT===============
@Composable
private fun AssisstancePromt( ){
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        )
        {
            Icon(Icons.Filled.Assistant, contentDescription = "Help")
            TextButton(onClick = {}) {
                Text("Trung tâm trợ giúp")
            }
            Image(painter = painterResource(R.drawable.icon_vn),
                contentDescription = "VN",
                modifier = Modifier.size(36.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Image(painter = painterResource(R.drawable.icon_britian),
                contentDescription = "EN",
                modifier = Modifier.size(36.dp))
        }
    }
}