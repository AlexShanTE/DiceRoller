package com.alex.sid.shante.diceroller.presentation.ui.gamescreen.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.alex.sid.shante.diceroller.R
import com.alex.sid.shante.diceroller.domain.models.D6
import com.alex.sid.shante.diceroller.domain.models.Dice
import com.alex.sid.shante.diceroller.domain.models.DiceType

@Composable
fun DiceWithImage(
    modifier: Modifier = Modifier,
    dice: Dice
) {

    val imageResource = dice.imageList[dice.currentValue - 1]

    //todo setup different dice
    when (dice.diceType) {
        DiceType.D6() -> {}
        DiceType.D3() -> {}
        DiceType.D12() -> {}
        DiceType.D20() -> {}
        DiceType.Custom() -> {}
        else -> {}
    }

    Image(
        modifier = modifier,
        painter = painterResource(imageResource),
        contentDescription = stringResource(R.string.image)
    )
}

@Preview(showBackground = true)
@Composable
fun DiceWithImagePreview() {
    DiceWithImage(dice = D6())
}