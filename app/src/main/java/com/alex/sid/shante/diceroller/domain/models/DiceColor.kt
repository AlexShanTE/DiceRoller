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
        override val front: Color = Color(0xFF8D3B8E),
        override val side: Color = Color(0xFF6F2282),
        override val back: Color = Color(0xFF692471)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class Blue(
        override val title: String = "Blue",
        override val front: Color = Color(0xFF4957A3),
        override val side: Color = Color(0xFF314C9B),
        override val back: Color = Color(0xFF33448F)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class DarkBlue(
        override val title: String = "DarkBlue",
        override val front: Color = Color(0xFF2A3188),
        override val side: Color = Color(0xFF2B317B),
        override val back: Color = Color(0xFF2B2F74)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class Aqua(
        override val title: String = "Aqua",
        override val front: Color = Color(0xFF66B5BE),
        override val side: Color = Color(0xFF22A7B3),
        override val back: Color = Color(0xFF00A8BD)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class LightGreen(
        override val title: String = "LightGreen",
        override val front: Color = Color(0xFF61B55F),
        override val side: Color = Color(0xFF37A935),
        override val back: Color = Color(0xFF3FAB35)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class DarkGreen(
        override val title: String = "DarkGreen",
        override val front: Color = Color(0xFF3A8B48),
        override val side: Color = Color(0xFF0A7F36),
        override val back: Color = Color(0xFF1D8135)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class Yellow(
        override val title: String = "Yellow",
        override val front: Color = Color(0xFFE6CC4E),
        override val side: Color = Color(0xFFDAC30D),
        override val back: Color = Color(0xFFDDC124)
    ) : DiceColor(title = title, front = front, side = side, back = back)

    data class Orange(
        override val title: String = "Red",
        override val front: Color = Color(0xFFE69C45),
        override val side: Color = Color(0xFFDA7D1A),
        override val back: Color = Color(0xFFDD6E0F)
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