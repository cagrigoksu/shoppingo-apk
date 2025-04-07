package com.cgu.shoppingo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cgu.shoppingo.ui.theme.ShoppinGoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppinGoTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){

    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Shop Now", Icons.Default.ShoppingCart),
        BottomNavItem("Profile", Icons.Default.Person)
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) },
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index }
                    )
                }
            }
        },
        content = { innerPadding ->
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)) {
                when (selectedIndex) {
                    0 -> HomeScreen()
                    1 -> ShopScreen()
                    2 -> ProfileScreen()
                }
            }
        }
    )
}

data class BottomNavItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@Composable
fun HomeScreen() {
    Text("🏠 Welcome to Home")
}

@Composable
fun ShopScreen() {
    Text("🛒 Your Cart")
}

@Composable
fun ProfileScreen() {
    Text("👤 Your Profile")
}
