package com.example.infoday

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "event")
data class Event(
    //val title: String, val deptId: String, var saved: Boolean
    @PrimaryKey val VenueID: String, val VenueName: String, val Latitude: Double, var Longitude: Double, var CampusID: String
) {
    companion object {
        val data = listOf(
            Event(VenueID = "POD", VenueName = "Podium, Level 6", Latitude = 22.340778, Longitude = 114.179943, CampusID = "HSH"),
            Event(VenueID = "SWT501", VenueName = "Shaw Tower 501", Latitude = 22.338867, Longitude = 114.181909, CampusID = "SHAW"),
            Event(VenueID = "LW", VenueName = "Lam Woo Intl. Conf. Centre", Latitude = 22.337639, Longitude = 114.181986, CampusID = "SHAW"),
            Event(VenueID = "LIP", VenueName = "Li Promenade", Latitude = 22.338376, Longitude = 114.182043, CampusID = "SHAW"),
            Event(VenueID = "OEE", VenueName = "Oen Hall Building (East)", Latitude = 22.340875, Longitude = 114.180315, CampusID = "HSH"),
            Event(VenueID = "JSCY", VenueName = "Courtyard, Student Residence Hall", Latitude = 22.335191, Longitude = 114.18233, CampusID = "BURC"),
            Event(VenueID = "MAINPO", VenueName = "William M.W.Mong Courtyard", Latitude = 22.340359, Longitude = 114.179905, CampusID = "HSH"),
            Event(VenueID = "WLB", VenueName = "Wing Lung Bank Bldg for Bus.", Latitude = 22.33779, Longitude = 114.18175, CampusID = "SHAW"),
            Event(VenueID = "AST", VenueName = "Sing Tao Building", Latitude = 22.341284, Longitude = 114.180171, CampusID = "HSH"),
            Event(VenueID = "AMP", VenueName = "Amphitheatre, Level 3", Latitude = 22.337959, Longitude = 114.181919, CampusID = "SHAW"),
            Event(VenueID = "SCT", VenueName = "Cha Chi-ming Science Tower", Latitude = 22.34064, Longitude = 114.18012, CampusID = "HSH"),
            Event(VenueID = "SCE", VenueName = "SCE Tower", Latitude = 22.336109, Longitude = 114.182735, CampusID = "BURC"),
            Event(VenueID = "WHSC", VenueName = "Wai Hang Sports Centre", Latitude = 22.339522, Longitude = 114.181659, CampusID = "HSH"),
            Event(VenueID = "JSC", VenueName = "Joint Sports Centre", Latitude = 22.337776, Longitude = 114.182298, CampusID = "SHAW"),
            Event(VenueID = "CHAP", VenueName = "University Chapel", Latitude = 22.341138, Longitude = 114.180205, CampusID = "HSH"),
            Event(VenueID = "SCM", VenueName = "Jockey Club SCM Building", Latitude = 22.335761, Longitude = 114.18245, CampusID = "BURC"),
            Event(VenueID = "CVA", VenueName = "Communication and Visual Arts Building", Latitude = 22.33431, Longitude = 114.182408, CampusID = "BURC"),
            Event(VenueID = "AC Hall", VenueName = "cademic Community Hall", Latitude = 22.341197, Longitude = 114.179763, CampusID = "HSH"),
            Event(VenueID = "LMC", VenueName = "Lui Ming Choi Centre", Latitude = 22.341, Longitude = 114.17974, CampusID = "HSH"),
            Event(VenueID = "FSC901E", VenueName = "Fong Shu Chuen Library Room 901E", Latitude = 22.340292, Longitude = 114.180157, CampusID = "HSH"),
            Event(VenueID = "ACC", VenueName = "Jockey Club Acad. Com. Centre", Latitude = 22.336146, Longitude = 114.182614, CampusID = "BURC"),
            Event(VenueID = "OEM", VenueName = "Oen Hall Building (Main)", Latitude = 22.34087, Longitude = 114.180015, CampusID = "HSH"),
            Event(VenueID = "OEW", VenueName = "Oen Hall Building (West)", Latitude = 22.340817, Longitude = 114.179599, CampusID = "HSH"),
            Event(VenueID = "RRS638", VenueName = "Sir Run Run Shaw Building Room 638", Latitude = 22.340252, Longitude = 114.179615, CampusID = "HSH"),
            Event(VenueID = "DLB", VenueName = "David C Lam Building", Latitude = 22.337419, Longitude = 114.181895, CampusID = "SHAW"),
            Event(VenueID = "AAB", VenueName = "Academic and Administration Building", Latitude = 22.33664, Longitude = 114.1824, CampusID = "SHAW"),

        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventScreen(navController: NavHostController, deptId: String?, type: String?) {
    val feeds = produceState(
        initialValue = listOf<Feed>(),
        producer = {
            value = KtorClient.getFeeds()

        }
    )


    LazyColumn {
        if (type.equals("Org")) {
            items(feeds.value.filter { it.organizer == deptId }) { event ->
                ListItem(
                    headlineText = {
                        Text(event.eventName)
                    },
                    //Bookmark an event
                    modifier = Modifier.clickable {
                        navController.navigate("event/map/${event.venue}")
                    }
                )
                Divider()
            }
        }else if (type.equals("Venues")){
            items(feeds.value.filter { it.venue == deptId }) { event ->
                ListItem(
                    headlineText = {
                        Text(event.eventName)
                    },
                    modifier = Modifier.clickable {
                        navController.navigate("venue/map/${event.venue}")

                    }
                )
                Divider()
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun EventPreview() {
//    InfodayTheme {
//        EventScreen("COMP")
//    }
//}