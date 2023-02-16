package com.alex.sid.shante.diceroller.presentation.ui.gamescreen

import com.alex.sid.shante.diceroller.domain.models.D6
import com.alex.sid.shante.diceroller.domain.models.Dice

data class GameState(
    var diceList: List<Dice> = listOf<Dice>(D6(), D6(), D6(), D6(), D6(), D6(), D6(), D6())
)