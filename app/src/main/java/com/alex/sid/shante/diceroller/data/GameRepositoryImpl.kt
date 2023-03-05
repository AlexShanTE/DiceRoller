package com.alex.sid.shante.diceroller.data

import com.alex.sid.shante.diceroller.domain.models.Dice
import com.alex.sid.shante.diceroller.domain.repositories.GameRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameRepositoryImpl : GameRepository {

    private val _diceList: MutableStateFlow<List<Dice>> = MutableStateFlow(
        listOf(Dice.D6dots(), Dice.D6dots(), Dice.D6dots())
    )
    override val diceList: StateFlow<List<Dice>> = _diceList.asStateFlow()

    override fun addDice() {
        _diceList.update {
            diceList.value.plus(Dice.D6dots())
        }
    }

    override fun removeDice(index: Int) {
        _diceList.update { diceList ->
            diceList.filterIndexed { i, _ ->
                i != index
            }
        }
    }

    override fun editDice(index: Int, newDice: Dice) {
        _diceList.update { diceList ->
            diceList.mapIndexed { i, dice ->
                if (i == index) newDice else dice
            }
        }
    }
}