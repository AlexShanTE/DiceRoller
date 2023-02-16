package com.alex.sid.shante.diceroller.domain.models

import com.alex.sid.shante.diceroller.R

sealed class Dice {
    abstract val title: String
    abstract val currentValue: Int
    abstract val imageList: List<Int>
    abstract fun copy(currentValue: Int, imageList: List<Int>): Dice
}

data class D6(
    override val title: String = "D6",
    override var currentValue: Int = 1,
    override val imageList: List<Int> = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )
) : Dice() {
    override fun copy(currentValue: Int, imageList: List<Int>): Dice =
        copy(title = title, currentValue = currentValue, imageList = imageList)
}

data class D3(
    override val title: String = "D3",
    override var currentValue: Int = 1,
    override val imageList: List<Int> = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3
    )
) : Dice() {
    override fun copy(currentValue: Int, imageList: List<Int>): Dice =
        copy(title = title, currentValue = currentValue, imageList = imageList)
}


