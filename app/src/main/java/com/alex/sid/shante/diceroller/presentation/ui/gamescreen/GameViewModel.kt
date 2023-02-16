package com.alex.sid.shante.diceroller.presentation.ui.gamescreen

import androidx.lifecycle.ViewModel
import com.alex.sid.shante.diceroller.domain.models.Dice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameState())
    val uiState: StateFlow<GameState> = _uiState.asStateFlow()

    fun rollThemAll() {
        val newDiceList: List<Dice> = uiState.value.diceList.map { dice ->
            dice.copy(
                currentValue = (0 until dice.imageList.size).random(),
                imageList = dice.imageList
            )
        }

        _uiState.update { currentState ->
            currentState.copy(diceList = newDiceList)
        }
    }

    fun rollDice(index: Int) {
        _uiState.update { currentState ->
            val diceNewValue = (0 until currentState.diceList[index].imageList.size).random()
            val newList = currentState.diceList.mapIndexed { i, dice ->
                if (i == index) dice.copy(
                    currentValue = diceNewValue,
                    imageList = dice.imageList
                ) else dice
            }
            currentState.copy(diceList = newList)
        }
    }
}