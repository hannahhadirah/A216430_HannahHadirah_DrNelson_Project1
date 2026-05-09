package com.example.a216430_hannahhadirah_drnelson_project1

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment

@Composable
fun RecipeScreen(navController: NavController, viewModel: FoodViewModel) {

    val expiringItems = viewModel.getExpiringSoon()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.15f)
            )
            .padding(16.dp)
            .verticalScroll(rememberScrollState())

    ) {

//        header
        AppHeader(
            title = "Recipes",
            subtitle = "Smart meal suggestions",
            showProfile = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (expiringItems.isEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Text(
                        "🥗",
                        fontSize = 42.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        "No Expiring Food",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        "Your food inventory looks fresh and well managed.",
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } else {

            expiringItems.forEach { food ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Text(
                                "🍽 Recipe Suggestion",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )

                            AssistChip(
                                onClick = {},
                                label = {
                                    Text("Urgent")
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = food.name,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Suggested Meal",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = getRecipe(food.name),
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

fun getRecipe(food: String): String {

    val item = food.lowercase()

    return when {

        // dairy category
        item.contains("milk") ||
                item.contains("cheese") ||
                item.contains("yogurt") ->
            "Creamy Pasta 🧀"

        // bread category
        item.contains("bread") ||
                item.contains("bun") ->
            "Toast Sandwich 🥪"

        // fruits category
        item.contains("banana") ||
                item.contains("apple") ||
                item.contains("orange") ||
                item.contains("strawberry") ||
                item.contains("mango") ->
            "Fruit Smoothie 🍓"

        // vegetables category
        item.contains("carrot") ||
                item.contains("broccoli") ||
                item.contains("cabbage") ||
                item.contains("spinach") ->
            "Vegetable Stir Fry 🍳"

        // rice or grains category
        item.contains("rice") ->
            "Fried Rice 🍚"

        // protein category
        item.contains("chicken") ||
                item.contains("egg") ->
            "Protein Bowl 🍳"

        // default fallback
        else ->
            "Healthy Mixed Meal 🌱"
    }
}

fun getFoodCategory(food: String): String {

    val item = food.lowercase()

    return when {

        item.contains("milk") ||
                item.contains("cheese") ||
                item.contains("yogurt") ->
            "Dairy 🥛"

        item.contains("banana") ||
                item.contains("apple") ||
                item.contains("orange") ||
                item.contains("strawberry") ||
                item.contains("mango") ->
            "Fruit 🍎"

        item.contains("carrot") ||
                item.contains("broccoli") ||
                item.contains("spinach") ->
            "Vegetable 🥦"

        item.contains("bread") ->
            "Bakery 🍞"

        item.contains("chicken") ||
                item.contains("egg") ->
            "Protein 🍗"

        else ->
            "General Food 🍽"
    }
}

fun getStorageTip(food: String): String {

    val item = food.lowercase()

    return when {

        item.contains("banana") ->
            "Store at room temperature for better freshness."

        item.contains("milk") ->
            "Keep refrigerated below 4°C."

        item.contains("bread") ->
            "Store in airtight container to prevent drying."

        item.contains("apple") ->
            "Keep refrigerated to extend shelf life."

        else ->
            "Store in a cool and dry place."
    }
}