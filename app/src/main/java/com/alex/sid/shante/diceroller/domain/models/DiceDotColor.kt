package com.alex.sid.shante.diceroller.domain.models

import androidx.compose.ui.graphics.Color

sealed class DiceDotColor(open val title: String, open val color: Color) {
    data class Red(
        override val title: String = "Red",
        override val color: Color = Color(0xFFFF0000)
    ) : DiceDotColor(title = title, color = color)

    data class Purple(
        override val title: String = "Purple",
        override val color: Color = Color(0xFFD900FF)
    ) : DiceDotColor(title = title, color = color)

    data class Blue(
        override val title: String = "Blue",
        override val color: Color = Color(0xFF0000FF)
    ) : DiceDotColor(title = title, color = color)

    data class DarkBlue(
        override val title: String = "DarkBlue",
        override val color: Color = Color(0xFF000789)
    ) : DiceDotColor(title = title, color = color)

    data class Aqua(
        override val title: String = "Aqua",
        override val color: Color = Color(0xFF00FFFF)
    ) : DiceDotColor(title = title, color = color)

    data class LightGreen(
        override val title: String = "LightGreen",
        override val color: Color = Color(0xFF00FF00)
    ) : DiceDotColor(title = title, color = color)

    data class DarkGreen(
        override val title: String = "DarkGreen",
        override val color: Color = Color(0xFF008400)
    ) : DiceDotColor(title = title, color = color)

    data class Yellow(
        override val title: String = "Yellow",
        override val color: Color = Color(0xFFFFFF00)
    ) : DiceDotColor(title = title, color = color)

    data class Orange(
        override val title: String = "Red",
        override val color: Color = Color(0xFFFFAA00)
    ) : DiceDotColor(title = title, color = color)

    data class Black(
        override val title: String = "Black",
        override val color: Color = Color(0xFF000000)
    ) : DiceDotColor(title = title, color = color)

    data class Grey(
        override val title: String = "Grey",
        override val color: Color = Color(0xFF575756)
    ) : DiceDotColor(title = title, color = color)

    data class White(
        override val title: String = "White",
        override val color: Color = Color(0xFFFFFFFF)
    ) : DiceDotColor(title = title, color = color)
}
