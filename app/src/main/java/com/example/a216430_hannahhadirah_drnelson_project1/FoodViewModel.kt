package com.example.a216430_hannahhadirah_drnelson_project1

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class FoodViewModel : ViewModel() {
//    use viewmodel to stores food data and keeps it alive when the screen rotates (configuration changes)
    private val _foodList = mutableStateListOf<FoodItem>()
    val foodList: List<FoodItem> = _foodList

    fun addFood(food: FoodItem) {
        _foodList.add(food)
    }

//    it calculates the number of days left between today’s date and the food expiry date using ChronoUnit.DAYS.between().
    fun getDaysLeft(expiryDate: LocalDate): Long {
        return ChronoUnit.DAYS.between(LocalDate.now(), expiryDate)
    }

    // get expiring items (<= 3 days)
    fun getExpiringSoon(): List<FoodItem> {
        return _foodList.filter {
            getDaysLeft(it.expiryDate) <= 3
        }
    }
}

//addFood() - adds food
//getDaysLeft() - calculates expiry days
//getExpiringSoon() - filters urgent items