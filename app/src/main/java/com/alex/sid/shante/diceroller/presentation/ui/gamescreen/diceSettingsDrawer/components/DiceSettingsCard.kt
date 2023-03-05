package com.alex.sid.shante.diceroller.presentation.ui.gamescreen.diceSettingsDrawer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex.sid.shante.diceroller.R
import com.alex.sid.shante.diceroller.domain.models.Dice
import com.alex.sid.shante.diceroller.domain.models.DiceColor
import com.alex.sid.shante.diceroller.domain.models.DiceDotColor
import com.alex.sid.shante.diceroller.domain.models.DiceEdgeColor
import com.alex.sid.shante.diceroller.domain.models.DiceType
import com.alex.sid.shante.diceroller.presentation.ui.common.GradientButton
import com.alex.sid.shante.diceroller.presentation.ui.gamescreen.components.DiceWithImage
import com.alex.sid.shante.diceroller.ui.theme.minionVariableConcept

@Composable
fun DiceSettingsCard(
    modifier: Modifier = Modifier,
    dice: Dice,
    onRemoveDiceClick: () -> Unit,
    onDiceTypeChanged: (DiceType) -> Unit,
    onDiceMaxValueChanged: (Int) -> Unit,
    onDiceColorChanged: (DiceColor) -> Unit,
    onDiceEdgeColorChanged: (DiceEdgeColor) -> Unit,
    onDiceDotColorChanged: (DiceDotColor) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 8.dp
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = modifier.width(125.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DiceWithImage(
                    dice = dice
                )
                GradientButton(
                    modifier = modifier
                        .padding(horizontal = 8.dp)
                        .padding(bottom = 4.dp),
                    elevation = ButtonDefaults.elevation(8.dp),
                    onClick = { onRemoveDiceClick() }
                ) {
                    Text(
                        text = stringResource(R.string.remove_dice),
                        textAlign = TextAlign.Center,
                        fontFamily = minionVariableConcept,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp
                    )
                }
            }
            DiceSettingsList(
                modifier = modifier.weight(1f),
                dice = dice,
                onDiceTypeChanged = { onDiceTypeChanged(it) },
                onDiceMaxValueChanged = { onDiceMaxValueChanged(it) },
                onDiceColorChanged = { onDiceColorChanged(it) },
                onDiceEdgeColorChanged = { onDiceEdgeColorChanged(it) },
                onDiceDotColorChanged = { onDiceDotColorChanged(it) },
            )
        }
    }
}