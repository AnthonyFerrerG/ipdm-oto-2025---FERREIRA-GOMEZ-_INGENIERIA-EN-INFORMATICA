package com.ejemplo.racetracker

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RaceScreen() {
    val viewModel: RaceViewModel = viewModel()
    val player1 by viewModel.player1.collectAsState()
    val player2 by viewModel.player2.collectAsState()
    val raceStatus by viewModel.raceStatus.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = raceStatus,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text("Jugador 1: $player1")
        Text("Jugador 2: $player2")
        Spacer(modifier = Modifier.height(32.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = { viewModel.startRace() }) {
                Text("Iniciar")
            }
            Button(onClick = { viewModel.pauseRace() }) {
                Text("Pausar")
            }
            Button(onClick = { viewModel.resetRace() }) {
                Text("Reiniciar")
            }
        }
    }
}
