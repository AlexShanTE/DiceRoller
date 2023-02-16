package com.alex.sid.shante.diceroller.presentation.ui.gamescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameState())
    val uiState: StateFlow<GameState> = _uiState.asStateFlow()

    fun rollThemAll() {
        uiState.value.diceList.forEachIndexed { index, _ ->
            rollDice(index)
        }
    }

    fun rollDice(index: Int) {
        tickerFlow(period = 50.milliseconds, duration = 1.seconds)
            .onEach { period ->
                _uiState.update { currentState ->
                    val newDiceList = currentState.diceList.mapIndexed { i, dice ->
                        if (i == index) dice.copy(
                            currentValue = (0 until dice.imageList.size).random(),
                            imageList = dice.imageList
                        ) else dice
                    }
                    currentState.copy(diceList = newDiceList)
                }
            }
            .launchIn(viewModelScope) // or lifecycleScope or other
    }

    private fun tickerFlow(
        period: Duration,
        duration: Duration,
        initialDelay: Duration = Duration.ZERO
    ) = flow {
        var currentTime: Duration = 0.seconds
        delay(initialDelay)
        while (duration > currentTime) {
            emit(duration)
            delay(period)
            currentTime += period
        }
    }
}