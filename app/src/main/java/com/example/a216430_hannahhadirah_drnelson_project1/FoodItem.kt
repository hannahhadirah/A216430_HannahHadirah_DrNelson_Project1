package com.example.a216430_hannahhadirah_drnelson_project1

import java.time.LocalDate


//data item to store the value input by users
//to structure data clearly and make it reusable across the app
data class FoodItem(
    val name: String,
    val expiryDate: LocalDate
)