package com.alex.sid.shante.diceroller.domain.models

import com.alex.sid.shante.diceroller.R

sealed class Dice {
    abstract val diceType: DiceType
    abstract val diceBorderColor: DiceBorderColor
    abstract val diceDotColor: DiceDotColor
    abstract val currentValue: Int
    abstract val imageList: List<Int>
    abstract fun copy(currentValue: Int): Dice
}

data class D6(
    override val diceType: DiceType = DiceType.D6(),
    override val diceBorderColor: DiceBorderColor = DiceBorderColor.White(),
    override val diceDotColor: DiceDotColor = DiceDotColor.Green(),
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
    override fun copy(currentValue: Int): Dice =
        copy(diceType = diceType, currentValue = currentValue, imageList = imageList)
}

data class D3(
    override val diceType: DiceType = DiceType.D3(),
    override val diceBorderColor: DiceBorderColor = DiceBorderColor.White(),
    override val diceDotColor: DiceDotColor = DiceDotColor.Green(),
    override var currentValue: Int = 1,
    override val imageList: List<Int> = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3
    )
) : Dice() {
    override fun copy(currentValue: Int): Dice =
        copy(diceType = diceType, currentValue = currentValue, imageList = imageList)
}

sealed class DiceType(open val title: String) {
    data class D6(override val title: String = "D6") : DiceType(title = title)
    data class D3(override val title: String = "D3") : DiceType(title = title)
    data class D12(override val title: String = "D12") : DiceType(title = title)
    data class D20(override val title: String = "D20") : DiceType(title = title)
    data class Custom(override val title: String = "Custom") : DiceType(title = title)
}

sealed class DiceBorderColor(open val title: String) {
    data class Blue(override val title: String = "Blue") : DiceBorderColor(title = title)
    data class Yellow(override val title: String = "Yellow") : DiceBorderColor(title = title)
    data class Red(override val title: String = "Red") : DiceBorderColor(title = title)
    data class Green(override val title: String = "Green") : DiceBorderColor(title = title)
    data class White(override val title: String = "White") : DiceBorderColor(title = title)
}

sealed class DiceDotColor(open val title: String) {
    data class Blue(override val title: String = "Blue") : DiceDotColor(title = title)
    data class Yellow(override val title: String = "Yellow") : DiceDotColor(title = title)
    data class Red(override val title: String = "Red") : DiceDotColor(title = title)
    data class Green(override val title: String = "Green") : DiceDotColor(title = title)
    data class White(override val title: String = "White") : DiceDotColor(title = title)
}

class DiceSettings {
    private val diceTypeList = listOf<DiceType>(
        DiceType.D3(),
        DiceType.D6(),
        DiceType.D12(),
        DiceType.D20(),
        DiceType.Custom()
    )

    private val diceBorderColor = listOf<DiceBorderColor>(
        DiceBorderColor.Blue(),
        DiceBorderColor.Yellow(),
        DiceBorderColor.Red(),
        DiceBorderColor.Green(),
        DiceBorderColor.White()
    )

    private val diceDiceDotColor = listOf<DiceDotColor>(
        DiceDotColor.Blue(),
        DiceDotColor.Yellow(),
        DiceDotColor.Red(),
        DiceDotColor.Green(),
        DiceDotColor.White()
    )

    fun diceDiceDotColorList() : List<DiceDotColor> {
        return diceDiceDotColor
    }

    fun diceBorderColorList() : List<DiceBorderColor> {
        return diceBorderColor
    }

    fun diceTypeList() : List<DiceType> {
        return diceTypeList
    }
}


