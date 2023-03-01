package com.alex.sid.shante.diceroller.presentation.ui.gamescreen

import com.alex.sid.shante.diceroller.domain.models.Dice

data class GameState(
    var diceList: List<Dice>,
    val total: Int = diceList.sumOf { it.currentValue }
)