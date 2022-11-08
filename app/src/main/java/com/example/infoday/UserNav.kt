package com.example.infoday


import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.infoday.ui.theme.InfodayTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(navController: NavHostController) {
    val name = "Martin Choy"
        Card(
            onClick = {

            },
            //modifier = Modifier.size(width = 180.dp, height = 100.dp)
        ) {
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                Box(Modifier.fillMaxSize()) {
                    Text(name)
                }
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Box(Modifier.fillMaxSize()){
                        Button(onClick = { }) {
                            Text("Register")
                        }

                    }
                    Box(Modifier.fillMaxSize()){
                        Button(onClick = {  }) {
                            Text("Address")
                        }

                    }
                }
            }





        }
        Divider()
    }




@Composable
fun UserNav(navController: NavHostController, snackbarHostState: SnackbarHostState) {
    NavHost(
        navController = navController,
        startDestination = "dept",
    ) {
        composable("dept") { UserScreen(navController) }
        composable("event/{deptId}") { backStackEntry ->
            EventScreen(navController, backStackEntry.arguments?.getString("deptId"), "Org")
        }
        composable("event/event2/{MapId}") { backStackEntry ->
            MapScreen(navController, snackbarHostState, backStackEntry.arguments?.getString("MapId"))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DeptPreview() {
//    InfodayTheme {
//        DeptNav(rememberNavController())
//    }
//}
