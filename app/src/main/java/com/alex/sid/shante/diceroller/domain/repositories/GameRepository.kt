package com.alex.sid.shante.diceroller.domain.repositories

import com.alex.sid.shante.diceroller.domain.models.Dice
import kotlinx.coroutines.flow.StateFlow

interface GameRepository {

    val diceList: StateFlow<List<Dice>>

    fun addDice()
    fun removeDice(index: Int)
    fun editDice(index: Int, newDice: Dice)

}