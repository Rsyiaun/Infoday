package com.example.infoday

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.infoday.ui.theme.InfodayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            InfodayTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    //Greeting("Android")
//                    ScaffoldScreen()
//                }
//            }
            ThemedContent()
        }
    }
}

@Composable
fun ThemedContent() {
    val dataStore = UserPreferences(LocalContext.current)
    var mode = dataStore.getMode.collectAsState(initial = false)
    //if null, return false   ?: false
    InfodayTheme(darkTheme = mode.value ?: false){
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            // Greeting("Android")
            ScaffoldScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreen() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf("Home", "Events", "Venue", "User")

    val navController = rememberNavController()

//    val feeds = produceState(
//        initialValue = listOf<Feed>(),
//        producer = {
//            value = getFeeds()
//        }
//    )
    //SnackbarHost
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("HKBU Event Management App") },
                //back to previous page function
                navigationIcon = {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    if (navBackStackEntry?.arguments?.getBoolean("topLevel") == false) {
                        IconButton(
                            onClick = { navController.navigateUp() }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    } else {
                        null
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(Icons.Filled.Favorite, contentDescription = item)
                        },
                        label = { Text(item) },
                        selected = selectedItem == index,
                        onClick = { selectedItem = index }
                    )
                }
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding),
            ) {
                when (selectedItem) {
                    0 -> FeedNav(navController, snackbarHostState) //(feeds.value)
                    //1 -> DeptNav(navController)
                    1 -> DeptNav(navController, snackbarHostState) //with DB
                    2 -> VenueNav(navController, snackbarHostState)
                    3 -> UserNav(navController, snackbarHostState)

                }
            }
        }
    )
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    InfodayTheme {
        //Greeting("Android")
        ScaffoldScreen()
    }
}