package com.example.infoday

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.infoday.ui.theme.InfodayTheme

//data class Dept(val name: String, val id: String) {
//    companion object {
//        val data = listOf(
//            Dept("Computer Science", "COMP"),
//            Dept("Communication Studies", "COMS")
//        )
//    }
//}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VenueScreen(navController: NavHostController) {
    //database
    val context = LocalContext.current
    val eventViewModel: EventViewModel = viewModel(
        factory = EventViewModelFactory(context.applicationContext as Application)
    )

    val events = eventViewModel.readAllData.observeAsState(listOf()).value.sortedBy { it.VenueID?.toString() }



    //api
//    val feeds = produceState(
//        initialValue = listOf<Feed>(),
//        producer = {
//            value = KtorClient.getFeeds()
//
//        }
//    )
//    val filteredFeeds= feeds.value.distinctBy { it.venue}
    LazyColumn {
        //items choose List import
        items(events) { dept ->
            ListItem(
                headlineText = { Text(dept.VenueID) },
                modifier = Modifier.clickable {
                    navController.navigate("venue/${dept.VenueID}")
                },
                leadingContent = {
                    Icon(
                        Icons.Filled.ThumbUp,
                        contentDescription = null
                    )
                }
            )
            Divider()
        }
    }
}



@Composable
fun VenueNav(navController: NavHostController, snackbarHostState: SnackbarHostState) {
    NavHost(
        navController = navController,
        startDestination = "dept",
    ) {
        composable("dept") { VenueScreen(navController) }
        composable("venue/{deptId}") { backStackEntry ->
            EventScreen(navController, backStackEntry.arguments?.getString("deptId"), "Venues")
        }
        composable("venue/event/{MapId}") { backStackEntry ->
            InfoScreen(navController, snackbarHostState, backStackEntry.arguments?.getString("MapId"))
        }
        composable("venue/map/{MapId}") { backStackEntry ->
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
