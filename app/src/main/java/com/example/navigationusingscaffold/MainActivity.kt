package com.example.navigationusingscaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationusingscaffold.ui.theme.NavigationUsingScaffoldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationUsingScaffoldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldApp()
                }
            }
        }
    }
}

/*
@Composable
fun scaffoldApp() {
    Scaffold(
        topBar = { MyTopBar("My App") },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                //background(color = MaterialTheme.colorScheme.primary)
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to the Home Screen!",
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)) {
                Text(
                    text = "This is the Bottom Bar",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(color = MaterialTheme.colorScheme.onPrimaryContainer)
                )
            }
        }
    )
}
*/

/*@Preview(showSystemUi = true)
@Composable
fun ScaffoldPreview() {
    NavigationUsingScaffoldTheme {
        scaffoldApp()
    }
}*/

/*//Walkthrough. Scaffold Exercise
@Composable
fun scaffoldApp() {
    Scaffold(
        topBar = { MyTopBar("My App") },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                //background(color = MaterialTheme.colorScheme.primary)
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to the Home Screen!",
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        },
        bottomBar = {
            BottomAppBar(modifier = Modifier.background(color = MaterialTheme.colorScheme.primary)) {
                Text(
                    text = "This is the Bottom Bar",
                    modifier = Modifier.padding(16.dp),
                    style = TextStyle(color = MaterialTheme.colorScheme.onPrimaryContainer)
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
 @Composable
fun MyTopBar(){
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(modifier = Modifier.background(color = MaterialTheme.colorScheme.onSecondaryContainer),
        title = {
            Text(
                text = "My App",
                style = TextStyle(color = MaterialTheme.colorScheme.onPrimaryContainer)
            )},

        navigationIcon = {
            IconButton(
                onClick = {/* TODO */},
            ) {
                Icon(Icons.Filled.Menu, contentDescription = null)
                }
            },
        actions = {
            IconButton(onClick = {
                expanded = !expanded
            }
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                //DropdownMenuItem(text = { Text(text = "Info")}, onClick = { TODO })
                //DropdownMenuItem(text = { Text(text = "Settings")}, onClick = { TODO })
            }
        }
    )
}*/

//Walkthrough. Scaffold and navigation
@Composable
fun ScaffoldApp(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Home"
    ) {
        composable(route = "Home") {
            MainScreen(navController)
        }
        composable(route = "Info"){
            InfoScreen(navController)
        }
        composable(route = "Settings"){
            SettingsScreen(navController)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ScaffoldPreview() {
    NavigationUsingScaffoldTheme {
        ScaffoldApp()
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController){
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(
                onClick = {
                    expanded = !expanded
            }
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded, 
                onDismissRequest = { expanded = false }) {
                DropdownMenuItem(
                    text = { Text(text = "Info")},
                    onClick = { navController.navigate("info") }
                )
                DropdownMenuItem(
                    text = { Text(text = "Settings")},
                    onClick = { navController.navigate("settings") }
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String, navController: NavController){
    TopAppBar(
        title = { Text(title)},
        navigationIcon = {
        IconButton(onClick = { navController.navigateUp() }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}

@Composable
fun MainScreen(navController: NavController) {
    Scaffold(
        topBar = { MainTopBar(title = "My App", navController) }
    ) { innerPadding ->
        // Use innerPadding to apply appropriate padding to the content. For simplicity, we pass it directly to Modifier.padding() of the Text composable.
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()  // Fill the max size of the parent
            .wrapContentSize(Alignment.Center)  // Center the content inside the Box
        ) {
            Text(text = "Welcome to the Home Screen!",
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.primaryContainer )
                    .padding (16.dp),
                fontSize = 20.sp, // Sets the font size
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary

            )
        }
    }
}
@Composable
fun InfoScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "Info", navController) }
    ) { innerPadding ->
        // Apply the padding within a Box composable as demonstrated before
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()  // Fill the max size of the parent
            .wrapContentSize(Alignment.Center)  // Center the content inside the Box
        ) {
            Text(text = "You have landed to Info View",
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.secondaryContainer )
                    .padding (16.dp),
                fontSize = 20.sp, // Sets the font size
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar(title = "Settings", navController) }
    ) { innerPadding ->
        // Similarly, apply the padding to avoid the content being hidden by the AppBar
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()  // Fill the max size of the parent
            .wrapContentSize(Alignment.Center)  // Center the content inside the Box
        ) {
            Text(text = "You have Landed to Setting View",
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                    .padding (16.dp),
                fontSize = 20.sp, // Sets the font size
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.tertiary
                )
        }
    }
}



