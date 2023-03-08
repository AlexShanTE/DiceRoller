package com.alex.sid.shante.diceroller.presentation.ui.gamescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex.sid.shante.diceroller.di.IoDispatcher
import com.alex.sid.shante.diceroller.domain.models.Dice
import com.alex.sid.shante.diceroller.domain.repositories.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: GameRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val diceList by repository.diceList::value

    private val _state = MutableStateFlow(GameState(diceList = diceList))
    val state: StateFlow<GameState> = _state.asStateFlow()

    fun addDice() {
        repository.addDice()
        updateUi()
    }

    fun removeDice(index: Int) {
        repository.removeDice(index)
        updateUi()

    }

    fun editDice(index: Int, dice: Dice) {
        repository.editDice(index, dice)
        updateUi()
    }

    private fun updateUi() {
        _state.update { state ->
            val total = diceList.sumOf { it.currentValue }
            state.copy(diceList = diceList, total = total)
        }
    }

    fun rollThemAll() {
        diceList.forEachIndexed { index, _ ->
            rollDice(index)
        }
    }

    fun rollDice(index: Int) {
        var counter = 0
        tickerFlow(period = 100.milliseconds, duration = 600.milliseconds)
            .onEach { period ->
                val dice = diceList[index]
                println(counter)
                editDice(index, dice.makeCopy(currentValue = generateNewValueOfDice(dice),))
                counter++
            }
            .flowOn(dispatcher)
            .launchIn(viewModelScope)
    }

    private fun generateNewValueOfDice(dice: Dice): Int {
        val oldValue = dice.currentValue
        var newValue = dice.currentValue
        while (newValue == oldValue) {
            newValue =
                if (dice is Dice.Custom)
                    (1..dice.maxValue).random()
                else (1..dice.imageList.size).random()
        }
        return newValue
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