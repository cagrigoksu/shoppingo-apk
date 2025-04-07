package com.cgu.shoppingo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Button
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
fun MainScreen() {
    val navController = rememberNavController()

    val items = listOf(
        BottomNavItem("Home", Icons.Default.Home),
        BottomNavItem("Shop Now", Icons.Default.ShoppingCart),
        BottomNavItem("Profile", Icons.Default.Person)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }

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
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") { HomeScreen() }
                composable("shop") { ShopScreen(navController) }
                composable("profile") { ProfileScreen() }
                composable("scan") { ScanScreen() }
            }

            when (selectedIndex) {
                0 -> navController.navigate("home")
                1 -> navController.navigate("shop")
                2 -> navController.navigate("profile")
            }
        }
    }
}

data class BottomNavItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

@Composable
fun HomeScreen() {
    Text("Home")
}

@Composable
fun ShopScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp)
        ) {

            Text("Your Cart")
        }

        Button(
            onClick = {
                navController.navigate("Scan")
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text("Scan Item")
        }
    }
}

@Composable
fun ProfileScreen() {
    Text("Profile")
}

@Composable
fun ScanScreen() {
    Text("Scan here")
}
