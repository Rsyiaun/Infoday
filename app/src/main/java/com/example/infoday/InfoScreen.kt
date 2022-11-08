package com.example.infoday

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
//switch import this
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

data class Contact(val office: String, val tel: String) {
    companion object {
        val data = listOf(
            Contact(office = "Admission Office", tel = "3411-2200"),
            Contact(office = "Emergencies", tel = "3411-7777"),
            Contact(office = "Health Services Center", tel = "3411-7447")
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneList(eventName: String?, navController: NavController) {
    val feeds = produceState(
        initialValue = listOf<Feed>(),
        producer = {
            value = KtorClient.getFeeds()
        }
    )
    LazyColumn {
        items(feeds.value.filter { it.eventName == eventName }) { feed ->
            Card(
                onClick = {

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
                    Column (horizontalAlignment = Alignment.CenterHorizontally){
                        Box(Modifier.fillMaxSize()){
                            Button(onClick = { }) {
                                Text("Register")
                            }

                        }
                        Box(Modifier.fillMaxSize()){
                            Button(onClick = { navController.navigate("feed/event/${feed.venue}") }) {
                                Text("Address")
                            }

                        }
                    }
                }
            }
            Divider()
        }
    }
}



@Composable
fun InfoScreen(navController: NavHostController, snackbarHostState: SnackbarHostState, eventName: String?) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        PhoneList(eventName,navController)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun InfoPreview() {
//    InfodayTheme {
//        InfoScreen()
//    }
//}
