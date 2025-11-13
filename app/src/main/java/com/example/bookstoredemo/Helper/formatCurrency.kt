package com.example.bookstoredemo.Helper

import java.text.DecimalFormat
import java.text.NumberFormat

fun formatCurrency(amout: Double): String{
    val locale = java.util.Locale("vi","VN")
    val formatter = NumberFormat.getCurrencyInstance(locale)
    return formatter.format(amout)
}