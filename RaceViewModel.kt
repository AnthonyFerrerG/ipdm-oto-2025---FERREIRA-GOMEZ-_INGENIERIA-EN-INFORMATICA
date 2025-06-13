package com.ejemplo.racetracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class RaceViewModel : ViewModel() {
    private var raceJob: Job? = null
    private var isRaceActive = false

    private val _player1 = MutableStateFlow(0)
    private val _player2 = MutableStateFlow(0)
    private val _raceStatus = MutableStateFlow("Preparados...")

    val player1: StateFlow<Int> = _player1.asStateFlow()
    val player2: StateFlow<Int> = _player2.asStateFlow()
    val raceStatus: StateFlow<String> = _raceStatus.asStateFlow()

    fun startRace() {
        if (isRaceActive) return
        isRaceActive = true
        _raceStatus.value = "¡Corriendo!"
        raceJob = viewModelScope.launch {
            while (isRaceActive) {
                delay(100)
                _player1.value += (1..3).random()
                _player2.value += (1..3).random()
                if (_player1.value >= 100 || _player2.value >= 100) {
                    _raceStatus.value = if (_player1.value >= 100) "¡Jugador 1 gana!" else "¡Jugador 2 gana!"
                    resetRace()
                }
            }
        }
    }

    fun pauseRace() {
        isRaceActive = false
        raceJob?.cancel()
        _raceStatus.value = "Pausado"
    }

    fun resetRace() {
        isRaceActive = false
        raceJob?.cancel()
        _player1.value = 0
        _player2.value = 0
        _raceStatus.value = "Preparados..."
    }
}
