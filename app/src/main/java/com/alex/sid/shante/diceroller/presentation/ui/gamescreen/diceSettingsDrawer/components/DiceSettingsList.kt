package com.alex.sid.shante.diceroller.presentation.ui.gamescreen.diceSettingsDrawer.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.alex.sid.shante.diceroller.R
import com.alex.sid.shante.diceroller.domain.models.Dice
import com.alex.sid.shante.diceroller.domain.models.DiceColor
import com.alex.sid.shante.diceroller.domain.models.DiceDotColor
import com.alex.sid.shante.diceroller.domain.models.DiceEdgeColor
import com.alex.sid.shante.diceroller.domain.models.DiceSettings
import com.alex.sid.shante.diceroller.domain.models.DiceType
import com.alex.sid.shante.diceroller.ui.theme.minionVariableConcept

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DiceSettingsList(
    modifier: Modifier = Modifier,
    dice: Dice,
    onDiceTypeChanged: (DiceType) -> Unit,
    onDiceMaxValueChanged: (Int) -> Unit,
    onDiceColorChanged: (DiceColor) -> Unit,
    onDiceEdgeColorChanged: (DiceEdgeColor) -> Unit,
    onDiceDotColorChanged: (DiceDotColor) -> Unit,
) {
    val diceSettings = DiceSettings()
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val basicMaxCustomDiceValue = Dice.Custom().maxValue
    var currentDiceMaxValue by remember { mutableStateOf(basicMaxCustomDiceValue.toString()) }
    var value by remember { mutableStateOf(basicMaxCustomDiceValue.toString()) }

    Column(
        modifier = modifier
    ) {
        DropDownMenu(
            dice = dice,
            listOfItems = diceSettings.diceTypeList().map { it.title },
            label = stringResource(R.string.type)
        ) { title ->
            val newDiceType =
                diceSettings.diceTypeList().find { it.title == title } ?: DiceType.D6dots()
            onDiceTypeChanged(newDiceType)
        }
        if (dice is Dice.Custom) {
            OutlinedTextField(
                value = value,
                onValueChange = {
                    if (it.isDigitsOnly() && it.length <= 4) {
                        value = it
                    }
                },
                textStyle = TextStyle(
                    fontFamily = minionVariableConcept,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colors.primary,
                    unfocusedBorderColor = MaterialTheme.colors.primary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) {
                            if (value.isEmpty()) {
                                value = dice.maxValue.toString()
                            } else {
                                if (currentDiceMaxValue.toInt() != value.toInt()) {
                                    if (value.length in 1..4) {
                                        currentDiceMaxValue = value
                                        onDiceMaxValueChanged(currentDiceMaxValue.toInt())
                                    } else onDiceMaxValueChanged(basicMaxCustomDiceValue)
                                }
                            }
                        }
                    },
                label = {
                    Text(
                        text = stringResource(R.string.max_value),
                        fontFamily = minionVariableConcept,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (value.isEmpty()) {
                            value = dice.maxValue.toString()
                        } else {
                            if (currentDiceMaxValue.toInt() != value.toInt()) {
                                if (value.length in 1..4) {
                                    currentDiceMaxValue = value
                                    onDiceMaxValueChanged(currentDiceMaxValue.toInt())
                                } else onDiceMaxValueChanged(basicMaxCustomDiceValue)
                            }
                        }
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }
                )
            )
        }
        DropDownMenu(
            dice = dice,
            listOfItems = diceSettings.diceColorList().map { it.title },
            label = stringResource(R.string.dice_color)
        ) { title ->
            val newDiceColor =
                diceSettings.diceColorList().find { it.title == title } ?: DiceColor.White()
            onDiceColorChanged(newDiceColor)
        }
        DropDownMenu(
            dice = dice,
            listOfItems = diceSettings.diceEdgeColorList().map { it.title },
            label = stringResource(R.string.edge_color)
        ) { title ->
            val newDiceEdgeColor =
                diceSettings.diceEdgeColorList().find { it.title == title } ?: DiceEdgeColor.Black()
            onDiceEdgeColorChanged(newDiceEdgeColor)
        }
        DropDownMenu(
            dice = dice,
            listOfItems = diceSettings.diceDotColorList().map { it.title },
            label = stringResource(R.string.dot_color)
        ) { title ->
            val newDiceColor =
                diceSettings.diceDotColorList().find { it.title == title } ?: DiceDotColor.Black()
            onDiceDotColorChanged(newDiceColor)
        }
    }
}