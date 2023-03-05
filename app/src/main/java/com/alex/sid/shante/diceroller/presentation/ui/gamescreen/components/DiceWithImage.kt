package com.alex.sid.shante.diceroller.presentation.ui.gamescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex.sid.shante.diceroller.R
import com.alex.sid.shante.diceroller.domain.models.Dice
import com.alex.sid.shante.diceroller.domain.models.DiceDotColor
import com.alex.sid.shante.diceroller.domain.models.DiceType
import com.alex.sid.shante.diceroller.ui.theme.minionVariableConcept

@Composable
fun DiceWithImage(
    modifier: Modifier = Modifier,
    dice: Dice
) {

    val diceDotResource =
        if (dice.diceType is DiceType.Custom) 0 else dice.imageList[dice.currentValue - 1]

    //todo setup different dice
    when (dice.diceType) {
        is DiceType.D4 -> {
            Box(modifier = modifier.size(140.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.d4_edge),
                    colorFilter = ColorFilter.tint(dice.diceEdgeColor.color),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d4_front),
                    colorFilter = ColorFilter.tint(dice.diceColor.front),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d4_back),
                    colorFilter = ColorFilter.tint(dice.diceColor.back),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(diceDotResource),
                    colorFilter = ColorFilter.tint(dice.diceDotColor.color),
                    contentDescription = null
                )
            }
        }
        is DiceType.D6dots -> {
            Box(modifier = modifier.size(140.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.d6_edge),
                    colorFilter = ColorFilter.tint(dice.diceEdgeColor.color),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d6_side),
                    colorFilter = ColorFilter.tint(dice.diceColor.side),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d6_back),
                    colorFilter = ColorFilter.tint(dice.diceColor.back),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d6_front),
                    colorFilter = ColorFilter.tint(dice.diceColor.front),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(diceDotResource),
                    colorFilter = ColorFilter.tint(dice.diceDotColor.color),
                    contentDescription = null
                )
            }
        }
        is DiceType.D6numbers -> {
            Box(modifier = modifier.size(140.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.d6_edge),
                    colorFilter = ColorFilter.tint(dice.diceEdgeColor.color),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d6_side),
                    colorFilter = ColorFilter.tint(dice.diceColor.side),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d6_back),
                    colorFilter = ColorFilter.tint(dice.diceColor.back),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d6_front),
                    colorFilter = ColorFilter.tint(dice.diceColor.front),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(diceDotResource),
                    colorFilter = ColorFilter.tint(dice.diceDotColor.color),
                    contentDescription = null
                )
            }
        }
        is DiceType.D8 -> {
            Box(modifier = modifier.size(140.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.d8_edge),
                    colorFilter = ColorFilter.tint(dice.diceEdgeColor.color),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d8_front),
                    colorFilter = ColorFilter.tint(dice.diceColor.front),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d8_side),
                    colorFilter = ColorFilter.tint(dice.diceColor.side),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d8_back),
                    colorFilter = ColorFilter.tint(dice.diceColor.back),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(diceDotResource),
                    colorFilter = ColorFilter.tint(dice.diceDotColor.color),
                    contentDescription = null
                )
            }
        }
        is DiceType.D10 -> {
            Box(modifier = modifier.size(140.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.d10_edge),
                    colorFilter = ColorFilter.tint(dice.diceEdgeColor.color),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d10_front),
                    colorFilter = ColorFilter.tint(dice.diceColor.front),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d10_side),
                    colorFilter = ColorFilter.tint(dice.diceColor.side),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d10_back),
                    colorFilter = ColorFilter.tint(dice.diceColor.back),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(diceDotResource),
                    colorFilter = ColorFilter.tint(dice.diceDotColor.color),
                    contentDescription = null
                )
            }
        }
        is DiceType.D12 -> {
            Box(modifier = modifier.size(140.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.d12_edge),
                    colorFilter = ColorFilter.tint(dice.diceEdgeColor.color),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d12_front),
                    colorFilter = ColorFilter.tint(dice.diceColor.front),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d12_side),
                    colorFilter = ColorFilter.tint(dice.diceColor.side),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d12_back),
                    colorFilter = ColorFilter.tint(dice.diceColor.back),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(diceDotResource),
                    colorFilter = ColorFilter.tint(dice.diceDotColor.color),
                    contentDescription = null
                )
            }
        }
        is DiceType.D20 -> {
            Box(modifier = modifier.size(140.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.d20_edge),
                    colorFilter = ColorFilter.tint(dice.diceEdgeColor.color),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d20_front),
                    colorFilter = ColorFilter.tint(dice.diceColor.front),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d20_side),
                    colorFilter = ColorFilter.tint(dice.diceColor.side),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d20_back),
                    colorFilter = ColorFilter.tint(dice.diceColor.back),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(diceDotResource),
                    colorFilter = ColorFilter.tint(dice.diceDotColor.color),
                    contentDescription = null
                )
            }
        }
        is DiceType.Custom -> {
            Box(
                modifier = modifier.size(140.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.d_custom_edge),
                    colorFilter = ColorFilter.tint(dice.diceEdgeColor.color),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d_custom_back),
                    colorFilter = ColorFilter.tint(dice.diceColor.back),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d_custom_side),
                    colorFilter = ColorFilter.tint(dice.diceColor.side),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.d_custom_front),
                    colorFilter = ColorFilter.tint(dice.diceColor.front),
                    contentDescription = null
                )
                Column(
                    modifier = modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = modifier.height(18.dp),
                        textAlign = TextAlign.Center,
                        text = "D${dice.maxValue}",
                        fontFamily = minionVariableConcept,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = dice.diceDotColor.color
                    )
                    Text(
                        modifier = modifier,
                        textAlign = TextAlign.Start,
                        text = dice.currentValue.toString(),
                        fontFamily = minionVariableConcept,
                        fontWeight = FontWeight.Bold,
                        fontSize = 40.sp,
                        color = dice.diceDotColor.color
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DiceWithImagePreview() {
    DiceWithImage(dice = Dice.D12(diceDotColor = DiceDotColor.Aqua()))
}