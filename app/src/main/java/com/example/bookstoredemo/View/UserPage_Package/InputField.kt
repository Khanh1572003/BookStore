package com.example.bookstoredemo.View.UserPage_Package


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import network.chaintech.ui.datepicker.WheelDatePickerView
import network.chaintech.utils.DateTimePickerView
import java.time.LocalDate

@Composable
fun InpurField(
    label:String,
    value:String,
    placeholder: String,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isError: Boolean = false,
    errorMessage: String? = null,
    leadingIcon: @Composable (()-> Unit)? =null,
    trailingIcon:@Composable (()-> Unit)? = null,
    onValueChange: (String)-> Unit
    ){
    Column(modifier=modifier,
            verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = label,
            style = MaterialTheme.typography.bodyMedium
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder) },
            singleLine = true,
            isError = isError,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            modifier = Modifier.fillMaxWidth()
        )
        if(isError && errorMessage !=null){
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
@Composable
fun DatePickerField(
    label: String,
    value: String,
    placeholder: String = "DD/MM/YYYY",
    modifier: Modifier = Modifier,
    onDateSelected: (String) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium
        )

        OutlinedTextField(
            value = value,
            onValueChange = {},
            placeholder = { Text(text = placeholder) },
            singleLine = true,
            readOnly = true,
            enabled = false,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Chọn ngày"
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                disabledTextColor = MaterialTheme.colorScheme.onSurface,
                disabledBorderColor = MaterialTheme.colorScheme.outline,
                disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDatePicker = true }
        )
    }

    // Wheel Date Picker Dialog
    if (showDatePicker) {
        WheelDatePickerView(
            height = 200.dp,
            showDatePicker = showDatePicker,
            dateTimePickerView = DateTimePickerView.DIALOG_VIEW,
            rowCount = 5,
            onDoneClick = { snappedDate ->
                val formattedDate = String.format(
                    "%02d/%02d/%d",
                    snappedDate.dayOfMonth,
                    snappedDate.month,
                    snappedDate.year
                )
                onDateSelected(formattedDate)
                showDatePicker = false
            },
            onDismiss = {
                showDatePicker = false
            },
            titleStyle = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = MaterialTheme.colorScheme.primary
            ),
            doneLabelStyle = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                color = MaterialTheme.colorScheme.primary
            ),
            yearsRange = 1920..LocalDate.now().year
        )
    }
}
