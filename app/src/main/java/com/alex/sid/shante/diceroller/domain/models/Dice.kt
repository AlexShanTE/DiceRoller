package com.alex.sid.shante.diceroller.domain.models

import com.alex.sid.shante.diceroller.R

sealed class Dice {
    abstract val diceType: DiceType
    abstract val currentValue: Int
    abstract val maxValue: Int
    abstract val diceColor: DiceColor
    abstract val diceEdgeColor: DiceEdgeColor
    abstract val diceDotColor: DiceDotColor
    abstract val imageList: List<Int>

    abstract fun makeCopy(diceType: DiceType, currentValue: Int, maxValue: Int, diceColor: DiceColor, diceEdgeColor: DiceEdgeColor, diceDotColor: DiceDotColor, imageList: List<Int>): Dice

    data class D4(
        override val diceType: DiceType = DiceType.D4(),
        override var currentValue: Int = 1,
        override var maxValue: Int = 4,
        override val diceColor: DiceColor = DiceColor.White(),
        override val diceEdgeColor: DiceEdgeColor = DiceEdgeColor.Black(),
        override val diceDotColor: DiceDotColor = DiceDotColor.Black(),
        override val imageList: List<Int> = listOf(
            R.drawable.d4_1,
            R.drawable.d4_2,
            R.drawable.d4_3,
            R.drawable.d4_4
        )
    ) : Dice() {
        override fun makeCopy(diceType: DiceType, currentValue: Int, maxValue: Int, diceColor: DiceColor, diceEdgeColor: DiceEdgeColor, diceDotColor: DiceDotColor, imageList: List<Int>): Dice =
            D4(diceType = diceType, currentValue = currentValue, maxValue = maxValue, diceColor = diceColor, diceEdgeColor = diceEdgeColor, diceDotColor = diceDotColor, imageList = imageList)
    }

    data class D6dots(
        override val diceType: DiceType = DiceType.D6dots(),
        override var currentValue: Int = 1,
        override val maxValue: Int = 6,
        override val diceColor: DiceColor = DiceColor.White(),
        override val diceEdgeColor: DiceEdgeColor = DiceEdgeColor.Black(),
        override val diceDotColor: DiceDotColor = DiceDotColor.Black(),
        override val imageList: List<Int> = listOf(
            R.drawable.d6_dots_1,
            R.drawable.d6_dots_2,
            R.drawable.d6_dots_3,
            R.drawable.d6_dots_4,
            R.drawable.d6_dots_5,
            R.drawable.d6_dots_6
        )
    ) : Dice() {
        override fun makeCopy(diceType: DiceType, currentValue: Int, maxValue: Int, diceColor: DiceColor, diceEdgeColor: DiceEdgeColor, diceDotColor: DiceDotColor, imageList: List<Int>): Dice =
            D6dots(diceType = diceType, currentValue = currentValue, maxValue = maxValue, diceColor = diceColor, diceEdgeColor = diceEdgeColor, diceDotColor = diceDotColor, imageList = imageList)
    }

    data class D6numbers(
        override val diceType: DiceType = DiceType.D6numbers(),
        override var currentValue: Int = 1,
        override val maxValue: Int = 6,
        override val diceColor: DiceColor = DiceColor.White(),
        override val diceEdgeColor: DiceEdgeColor = DiceEdgeColor.Black(),
        override val diceDotColor: DiceDotColor = DiceDotColor.Black(),
        override val imageList: List<Int> = listOf(
            R.drawable.d6_numbers_1,
            R.drawable.d6_numbers_2,
            R.drawable.d6_numbers_3,
            R.drawable.d6_numbers_4,
            R.drawable.d6_numbers_5,
            R.drawable.d6_numbers_6
        )
    ) : Dice() {
        override fun makeCopy(diceType: DiceType, currentValue: Int, maxValue: Int, diceColor: DiceColor, diceEdgeColor: DiceEdgeColor, diceDotColor: DiceDotColor, imageList: List<Int>): Dice =
            D6numbers(diceType = diceType, currentValue = currentValue, maxValue = maxValue, diceColor = diceColor, diceEdgeColor = diceEdgeColor, diceDotColor = diceDotColor, imageList = imageList)
    }

    data class D8(
        override val diceType: DiceType = DiceType.D8(),
        override var currentValue: Int = 1,
        override var maxValue: Int = 8,
        override val diceColor: DiceColor = DiceColor.White(),
        override val diceEdgeColor: DiceEdgeColor = DiceEdgeColor.Black(),
        override val diceDotColor: DiceDotColor = DiceDotColor.Black(),
        override val imageList: List<Int> = listOf(
            R.drawable.d8_1,
            R.drawable.d8_2,
            R.drawable.d8_3,
            R.drawable.d8_4,
            R.drawable.d8_5,
            R.drawable.d8_6,
            R.drawable.d8_7,
            R.drawable.d8_8
        )
    ) : Dice() {
        override fun makeCopy(diceType: DiceType, currentValue: Int, maxValue: Int, diceColor: DiceColor, diceEdgeColor: DiceEdgeColor, diceDotColor: DiceDotColor, imageList: List<Int>): Dice =
            D8(diceType = diceType, currentValue = currentValue, maxValue = maxValue, diceColor = diceColor, diceEdgeColor = diceEdgeColor, diceDotColor = diceDotColor, imageList = imageList)
    }

    data class D10(
        override val diceType: DiceType = DiceType.D10(),
        override var currentValue: Int = 1,
        override val maxValue: Int = 10,
        override val diceColor: DiceColor = DiceColor.White(),
        override val diceEdgeColor: DiceEdgeColor = DiceEdgeColor.Black(),
        override val diceDotColor: DiceDotColor = DiceDotColor.Black(),
        override val imageList: List<Int> = listOf(
            R.drawable.d10_1,
            R.drawable.d10_2,
            R.drawable.d10_3,
            R.drawable.d10_4,
            R.drawable.d10_5,
            R.drawable.d10_6,
            R.drawable.d10_7,
            R.drawable.d10_8,
            R.drawable.d10_9,
            R.drawable.d10_10,
        )
    ) : Dice() {
        override fun makeCopy(diceType: DiceType, currentValue: Int, maxValue: Int, diceColor: DiceColor, diceEdgeColor: DiceEdgeColor, diceDotColor: DiceDotColor, imageList: List<Int>): Dice =
            D10(diceType = diceType, currentValue = currentValue, maxValue = maxValue, diceColor = diceColor, diceEdgeColor = diceEdgeColor, diceDotColor = diceDotColor, imageList = imageList)
    }

    data class D12(
        override val diceType: DiceType = DiceType.D12(),
        override var currentValue: Int = 1,
        override val maxValue: Int = 12,
        override val diceColor: DiceColor = DiceColor.White(),
        override val diceEdgeColor: DiceEdgeColor = DiceEdgeColor.Black(),
        override val diceDotColor: DiceDotColor = DiceDotColor.Black(),
        override val imageList: List<Int> = listOf(
            R.drawable.d12_1,
            R.drawable.d12_2,
            R.drawable.d12_3,
            R.drawable.d12_4,
            R.drawable.d12_5,
            R.drawable.d12_6,
            R.drawable.d12_7,
            R.drawable.d12_8,
            R.drawable.d12_9,
            R.drawable.d12_10,
            R.drawable.d12_11,
            R.drawable.d12_12,
        )
    ) : Dice() {
        override fun makeCopy(diceType: DiceType, currentValue: Int, maxValue: Int, diceColor: DiceColor, diceEdgeColor: DiceEdgeColor, diceDotColor: DiceDotColor, imageList: List<Int>): Dice =
            D12(diceType = diceType, currentValue = currentValue, maxValue = maxValue, diceColor = diceColor, diceEdgeColor = diceEdgeColor, diceDotColor = diceDotColor, imageList = imageList)
    }

    data class D20(
        override val diceType: DiceType = DiceType.D20(),
        override var currentValue: Int = 1,
        override val maxValue: Int = 20,
        override val diceColor: DiceColor = DiceColor.White(),
        override val diceEdgeColor: DiceEdgeColor = DiceEdgeColor.Black(),
        override val diceDotColor: DiceDotColor = DiceDotColor.Black(),
        override val imageList: List<Int> = listOf(
            R.drawable.d20_1,
            R.drawable.d20_2,
            R.drawable.d20_3,
            R.drawable.d20_4,
            R.drawable.d20_5,
            R.drawable.d20_6,
            R.drawable.d20_7,
            R.drawable.d20_8,
            R.drawable.d20_9,
            R.drawable.d20_10,
            R.drawable.d20_11,
            R.drawable.d20_12,
            R.drawable.d20_13,
            R.drawable.d20_14,
            R.drawable.d20_15,
            R.drawable.d20_16,
            R.drawable.d20_17,
            R.drawable.d20_18,
            R.drawable.d20_19,
            R.drawable.d20_20,
        )
    ) : Dice() {
        override fun makeCopy(diceType: DiceType, currentValue: Int, maxValue: Int, diceColor: DiceColor, diceEdgeColor: DiceEdgeColor, diceDotColor: DiceDotColor, imageList: List<Int>): Dice =
            D20(diceType = diceType, currentValue = currentValue, maxValue = maxValue, diceColor = diceColor, diceEdgeColor = diceEdgeColor, diceDotColor = diceDotColor, imageList = imageList)
    }

    data class Custom(
        override var currentValue: Int = 1,
        override val maxValue: Int = 100,
        override val diceType: DiceType = DiceType.Custom(),
        override val diceColor: DiceColor = DiceColor.White(),
        override val diceEdgeColor: DiceEdgeColor = DiceEdgeColor.Black(),
        override val diceDotColor: DiceDotColor = DiceDotColor.Black(),
        override val imageList: List<Int> = emptyList()
    ) : Dice() {
        override fun makeCopy(diceType: DiceType, currentValue: Int, maxValue: Int, diceColor: DiceColor, diceEdgeColor: DiceEdgeColor, diceDotColor: DiceDotColor, imageList: List<Int>): Dice =
            Custom(diceType = diceType, currentValue = currentValue, maxValue = maxValue, diceColor = diceColor, diceEdgeColor = diceEdgeColor, diceDotColor = diceDotColor, imageList = imageList)
    }
}


sealed class DiceType(open val title: String) {
    data class D4(override val title: String = "D4") : DiceType(title = title)
    data class D6dots(override val title: String = "D6 with dots") : DiceType(title = title)
    data class D6numbers(override val title: String = "D6 with numbers") : DiceType(title = title)
    data class D8(override val title: String = "D8") : DiceType(title = title)
    data class D10(override val title: String = "D10") : DiceType(title = title)
    data class D12(override val title: String = "D12") : DiceType(title = title)
    data class D20(override val title: String = "D20") : DiceType(title = title)
    data class Custom(override val title: String = "Custom") : DiceType(title = title)
}

class DiceSettings {

    private val diceList: List<Dice> = listOf(
        Dice.D4(), Dice.D6dots(),Dice.D6numbers(), Dice.D8(), Dice.D10(), Dice.D12(), Dice.D20(), Dice.Custom()
    )
    private val diceTypeList: List<DiceType> = diceList.map { it.diceType }
    private val diceColorList: List<DiceColor> = listOf(
        DiceColor.Red(),
        DiceColor.Purple(),
        DiceColor.Blue(),
        DiceColor.DarkBlue(),
        DiceColor.Aqua(),
        DiceColor.LightGreen(),
        DiceColor.DarkGreen(),
        DiceColor.Yellow(),
        DiceColor.Orange(),
        DiceColor.Grey(),
        DiceColor.White()
    )
    private val diceEdgeColorList: List<DiceEdgeColor> = listOf(
        DiceEdgeColor.Red(),
        DiceEdgeColor.Purple(),
        DiceEdgeColor.Blue(),
        DiceEdgeColor.DarkBlue(),
        DiceEdgeColor.Aqua(),
        DiceEdgeColor.LightGreen(),
        DiceEdgeColor.DarkGreen(),
        DiceEdgeColor.Yellow(),
        DiceEdgeColor.Orange(),
        DiceEdgeColor.Black(),
        DiceEdgeColor.Grey(),
        DiceEdgeColor.White()
    )
    private val diceDotColorList: List<DiceDotColor> = listOf(
        DiceDotColor.Red(),
        DiceDotColor.Purple(),
        DiceDotColor.Blue(),
        DiceDotColor.DarkBlue(),
        DiceDotColor.Aqua(),
        DiceDotColor.LightGreen(),
        DiceDotColor.DarkGreen(),
        DiceDotColor.Yellow(),
        DiceDotColor.Orange(),
        DiceDotColor.Black(),
        DiceDotColor.Grey(),
        DiceDotColor.White()
    )

    fun diceDotColorList(): List<DiceDotColor> = diceDotColorList

    fun diceColorList(): List<DiceColor> = diceColorList

    fun diceEdgeColorList(): List<DiceEdgeColor> = diceEdgeColorList

    fun diceTypeList(): List<DiceType> = diceTypeList

    fun diceList(): List<Dice> = diceList
}


