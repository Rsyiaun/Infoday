package com.example.infoday

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


data class Building(val title: String, val latitude: Double, val longitude: Double) {
    companion object {

    }
}

@Composable
fun MapScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    string: String?
) {
    val context = LocalContext.current
    val eventViewModel: EventViewModel = viewModel(
        factory = EventViewModelFactory(context.applicationContext as Application)
    )

    val events = eventViewModel.readAllData.observeAsState(listOf()).value.distinctBy { it.VenueID == string }



    val hkbu = LatLng(22.33787, 114.18131)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(hkbu, 17f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        events.forEach {

            Marker(
                state = MarkerState(
                    position = LatLng(
                        it.Latitude,
                        it.Longitude
                    )
                ),
                title = it.VenueName,
                snippet = string
            )
        }


        }
    }

