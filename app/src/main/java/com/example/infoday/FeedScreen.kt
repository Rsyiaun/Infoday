package com.example.infoday

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
import kotlinx.serialization.Serializable

@Serializable
data class Feed(
    //val id: Int, val image: String, val title: String, val detail: String
//json attribute
    val id: Int,
    val eventName: String,
    val shortDescription: String,
    val longDescription: String,
    val imageUrl: String,
    val organizer: String,
    val eventDate: String,
    val startTime: String,
    val endTime: String,
    val venue: String,
    val quota: Int,
    val highlightEvent: String,
    val createdAt: String,
    val updatedAt: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(navController: NavHostController) { //(feeds: List<Feed>)
    val feeds = produceState(
        initialValue = listOf<Feed>(),
        producer = {
            value = KtorClient.getFeeds()
        }
    )


    LazyColumn {
        items(feeds.value) { feed ->
            Card(
                onClick = {
                    navController.navigate("feed/${feed.eventName}")
                          },
                //modifier = Modifier.size(width = 180.dp, height = 100.dp)
            ) {
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    AsyncImage(
                        model = feed.imageUrl,
                        contentDescription = null
                    )
                    Box(Modifier.fillMaxSize()) {
                        Text(feed.eventName)

                    }
                    Box(Modifier.fillMaxSize()) {
                        Text(feed.venue)

                    }
                    Box(Modifier.fillMaxSize()) {
                        Text("Date:" + feed.eventDate)

                    }

                }

            }
            Divider()
        }
    }
}

@Composable
fun FeedNav(navController: NavHostController, snackbarHostState: SnackbarHostState) {
    NavHost(
        navController = navController,
        startDestination = "feed",
    ) {
        composable("feed") { FeedScreen(navController) }
        composable("feed/{deptId}") { backStackEntry ->
            InfoScreen(navController, snackbarHostState, backStackEntry.arguments?.getString("deptId"))
        }
        composable("feed/event/{MapId}") { backStackEntry ->
            MapScreen(navController, snackbarHostState, backStackEntry.arguments?.getString("MapId"))
        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun FeedPreview() {
//    InfodayTheme {
//        FeedScreen(Feed.data)
//    }
//}