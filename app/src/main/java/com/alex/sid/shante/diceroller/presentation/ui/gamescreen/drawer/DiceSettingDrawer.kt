package com.alex.sid.shante.diceroller.presentation.ui.gamescreen.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.core.text.isDigitsOnly
import com.alex.sid.shante.diceroller.R
import com.alex.sid.shante.diceroller.domain.models.Dice
import com.alex.sid.shante.diceroller.domain.models.DiceColor
import com.alex.sid.shante.diceroller.domain.models.DiceDotColor
import com.alex.sid.shante.diceroller.domain.models.DiceEdgeColor
import com.alex.sid.shante.diceroller.domain.models.DiceSettings
import com.alex.sid.shante.diceroller.domain.models.DiceType
import com.alex.sid.shante.diceroller.presentation.ui.common.GradientButton
import com.alex.sid.shante.diceroller.presentation.ui.gamescreen.components.DiceWithImage
import com.alex.sid.shante.diceroller.ui.theme.minionVariableConcept

@Composable
fun DrawerHeader(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            fontFamily = minionVariableConcept,
            fontWeight = FontWeight.Bold,
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DrawerBody(
    modifier: Modifier = Modifier,
    diceList: List<Dice>,
    onRemoveDiceClicked: (Int) -> Unit,
    onDiceTypeChanged: (Int, DiceType) -> Unit,
    onDiceMaxValueChanged: (Int, Int) -> Unit,
    onDiceColorChanged: (Int, DiceColor) -> Unit,
    onDiceEdgeColorChanged: (Int, DiceEdgeColor) -> Unit,
    onDiceDotColorChanged: (Int, DiceDotColor) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val nestedScrollConnection =
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                keyboardController?.hide()
                focusManager.clearFocus()
                return Offset.Zero
            }
        }

    LazyColumn(
        modifier = modifier
            .clickable {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
            .nestedScroll(nestedScrollConnection)
    ) {
        itemsIndexed(diceList) { index, dice ->
            DiceSettingCard(
                modifier = modifier,
                dice = dice,
                onRemoveDiceClick = { onRemoveDiceClicked(index) },
                onDiceTypeChanged = { onDiceTypeChanged(index, it) },
                onDiceMaxValueChanged = { onDiceMaxValueChanged(index, it) },
                onDiceColorChanged = { onDiceColorChanged(index, it) },
                onDiceEdgeColorChanged = { onDiceEdgeColorChanged(index, it) },
                onDiceDotColorChanged = { onDiceDotColorChanged(index, it) }
            )
        }
    }
}

@Composable
fun DiceSettingCard(
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
                onValueChange = { if (it.isDigitsOnly() && it.length <= 4) {
                    value = it
                }},
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

@Composable
fun DropDownMenu(
    dice: Dice,
    listOfItems: List<String>,
    label: String,
    onDiceSettingsChanged: (String) -> Unit
) {

    val type = stringResource(id = R.string.type)
    val borderColor = stringResource(id = R.string.dice_color)
    val edgeColor = stringResource(id = R.string.edge_color)
    val dotColor = stringResource(id = R.string.dot_color)

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember {
        mutableStateOf(
            when (label) {
                type -> dice.diceType.title
                borderColor -> dice.diceColor.title
                edgeColor -> dice.diceEdgeColor.title
                dotColor -> dice.diceDotColor.title
                else -> ""
            }
        )
    }

    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon =
        if (expanded) Icons.Filled.KeyboardArrowUp
        else Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(5.dp)) {
        OutlinedTextField(
            value = selectedText,
            textStyle = TextStyle(
                fontFamily = minionVariableConcept,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            onValueChange = { selectedText = it },
            readOnly = false,
            enabled = false,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                disabledTextColor = LocalContentColor.current.copy(LocalContentAlpha.current),
                disabledBorderColor = MaterialTheme.colors.primary,
                disabledLabelColor = LocalContentColor.current.copy(LocalContentAlpha.current)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(
                text = label,
                fontFamily = minionVariableConcept,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ) },
            trailingIcon = {
                Icon(
                    modifier = Modifier.clickable { expanded = !expanded },
                    imageVector = icon,
                    contentDescription = stringResource(R.string.show_options),
                    tint = MaterialTheme.colors.primary
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            listOfItems.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                    onDiceSettingsChanged(selectedText)
                }) {
                    Text(
                        text = label,
                        fontFamily = minionVariableConcept,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DrawerBodyPreview() {
    DrawerBody(
        diceList = listOf(Dice.D6dots(), Dice.D6dots(), Dice.D6dots(), Dice.D6dots()),
        onRemoveDiceClicked = {},
        onDiceTypeChanged = { _, _ -> },
        onDiceMaxValueChanged = { _, _ -> },
        onDiceColorChanged = { _, _ -> },
        onDiceEdgeColorChanged = { _, _ -> },
        onDiceDotColorChanged = { _, _ -> }
    )
}