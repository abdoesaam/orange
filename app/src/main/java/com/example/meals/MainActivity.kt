package com.example.meals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.lifecycleScope
import com.example.meals.ui.theme.MealsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel : MealsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MealsTheme { Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) { Greeting("Android") } } }

        val rv: RecyclerView = findViewById(R.id.category_rv)

        viewModel.getMeals()
        val mealsAdapter = MealsAdapter()
        lifecycleScope.launch {
            viewModel.categories.collect{
            mealsAdapter.submitList(it?.categories)
            rv.adapter = mealsAdapter
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MealsTheme {
        Greeting("Android")
    }
}