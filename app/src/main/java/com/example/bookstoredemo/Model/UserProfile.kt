package com.example.bookstoredemo.Model

data class UserProfile(
    var fullName: String="",
    var email:String="",
    var phone:String="",
    var dateofBirth:String="",
    var gender:String=""
){
    fun isValidEmail(): Boolean=
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    fun isValidPhone(): Boolean =
        phone.length in 10..11 && phone.all { it.isDigit() }
    fun isComplete(): Boolean=
                fullName.isNotBlank() && email.isNotBlank() &&
                phone.isNotBlank() &&
                dateofBirth.isNotBlank() &&
                gender.isNotBlank()

}

