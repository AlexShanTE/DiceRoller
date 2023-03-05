package com.alex.sid.shante.diceroller.domain.models

import androidx.compose.ui.graphics.Color

sealed class DiceColor(
    open val title: String,
    open val front: Color,
    open val side: Color,
    open val back: Color
) {
    data class Red(
        override val title: String = "Red",
        override val front: Color = Color(0xFFB3182A),
        override val side: Color = Color(0xFF871531),
        override val back: Color = Color(0xFF702330)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class Purple(
        override val title: String = "Purple",
        override val front: Color = Color(0xFF963B8E),
        override val side: Color = Color(0xFF7C2572),
        override val back: Color = Color(0xFF4F1C4C)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class Blue(
        override val title: String = "Blue",
        override val front: Color = Color(0xFF0E85B3),
        override val side: Color = Color(0xFF235B7C),
        override val back: Color = Color(0xFF0F3B51)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class DarkBlue(
        override val title: String = "DarkBlue",
        override val front: Color = Color(0xFF254B9B),
        override val side: Color = Color(0xFF223979),
        override val back: Color = Color(0xFF20284C)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class Aqua(
        override val title: String = "Aqua",
        override val front: Color = Color(0xFF36B7B6),
        override val side: Color = Color(0xFF1C9FA3),
        override val back: Color = Color(0xFF266976)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class LightGreen(
        override val title: String = "LightGreen",
        override val front: Color = Color(0xFF35A936),
        override val side: Color = Color(0xFF257D34),
        override val back: Color = Color(0xFF165226)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class DarkGreen(
        override val title: String = "DarkGreen",
        override val front: Color = Color(0xFF2A8636),
        override val side: Color = Color(0xFF1B5929),
        override val back: Color = Color(0xFF132E1B)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class Yellow(
        override val title: String = "Yellow",
        override val front: Color = Color(0xFFB1B315),
        override val side: Color = Color(0xFF747C26),
        override val back: Color = Color(0xFF4E521D)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class Orange(
        override val title: String = "Red",
        override val front: Color = Color(0xFFB57F18),
        override val side: Color = Color(0xFF7F642C),
        override val back: Color = Color(0xFF543F17)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class Grey(
        override val title: String = "Grey",
        override val front: Color = Color(0xFF9D9D9C),
        override val side: Color = Color(0xFF706F6F),
        override val back: Color = Color(0xFF575756)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class White(
        override val title: String = "White",
        override val front: Color = Color(0xFFFFFFFF),
        override val side: Color = Color(0xFFFFFFFF),
        override val back: Color = Color(0xFFFFFFFF)
    ) : DiceColor(title = title, front = front, side = side, back = back)
}