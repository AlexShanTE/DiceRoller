package com.alex.sid.shante.diceroller.presentation.ui.gamescreen.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.alex.sid.shante.diceroller.R
import com.alex.sid.shante.diceroller.domain.models.D6
import com.alex.sid.shante.diceroller.domain.models.Dice
import com.alex.sid.shante.diceroller.domain.models.DiceSettings
import com.alex.sid.shante.diceroller.domain.models.DiceType
import com.alex.sid.shante.diceroller.presentation.ui.gamescreen.components.DiceWithImage

@Composable
fun DrawerHeader(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text)
    }
}

@Composable
fun DrawerBody(
    modifier: Modifier = Modifier,
    diceList: List<Dice>,
    onRemoveDiceClicked: (Int) -> Unit,
    onDiceTypeChanged: (Int, DiceType) -> Unit,
    onDiceBorderColorChanged: (Int, String) -> Unit,
    onDiceDotColorChanged: (Int, String) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        itemsIndexed(diceList) { index, dice ->
            DiceSettingItem(
                modifier = modifier,
                dice = dice,
                onRemoveDiceClick = { onRemoveDiceClicked(index) },
                onDiceTypeChanged = { onDiceTypeChanged(index, it) },
                onDiceBorderColorChanged = { onDiceBorderColorChanged(index, it) },
                onDiceDotColorChanged = { onDiceDotColorChanged(index, it) }
            )
        }
    }
}

@Composable
fun DiceSettingItem(
    modifier: Modifier = Modifier,
    dice: Dice,
    onRemoveDiceClick: () -> Unit,
    onDiceTypeChanged: (DiceType) -> Unit,
    onDiceBorderColorChanged: (String) -> Unit,
    onDiceDotColorChanged: (String) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 12.dp
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
                    modifier = modifier
                        .width(140.dp)
                        .aspectRatio(1f),
                    dice = dice
                )
                Button(
                    modifier = modifier
                        .padding(horizontal = 8.dp)
                        .padding(bottom = 4.dp),
                    elevation = ButtonDefaults.elevation(8.dp),
                    onClick = { onRemoveDiceClick() }
                ) {
                    Text(
                        text = stringResource(R.string.remove_dice),
                        textAlign = TextAlign.Center
                    )
                }
            }
            DiceSettingsList(
                modifier = modifier.weight(1f),
                dice = dice,
                onDiceTypeChanged = { onDiceTypeChanged(it) },
                onDiceBorderColorChanged = { onDiceBorderColorChanged(it) },
                onDiceDotColorChanged = { onDiceDotColorChanged(it) }
            )
        }
    }
}

@Composable
fun DiceSettingsList(
    modifier: Modifier = Modifier,
    dice: Dice,
    onDiceTypeChanged: (DiceType) -> Unit,
    onDiceBorderColorChanged: (String) -> Unit,
    onDiceDotColorChanged: (String) -> Unit,
) {

    val diceSettings = DiceSettings()

    Column() {
        DropDownMenu(
            dice = dice,
            listOfItems = diceSettings.diceTypeList().map { it.title },
            label = stringResource(R.string.type)
        ) { str ->
            val newDiceType = diceSettings.diceTypeList().find { it.title == str }
            if (newDiceType != null) {
                onDiceTypeChanged(newDiceType)
            }
        }
        DropDownMenu(
            dice = dice,
            listOfItems = diceSettings.diceBorderColorList().map { it.title },
            label = stringResource(R.string.border_color)
        ) {
            onDiceBorderColorChanged(it)
        }
        DropDownMenu(
            dice = dice,
            listOfItems = diceSettings.diceDiceDotColorList().map { it.title },
            label = stringResource(R.string.dot_color)
        ) {
            onDiceDotColorChanged(it)
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
    val borderColor = stringResource(id = R.string.border_color)
    val dotColor = stringResource(id = R.string.dot_color)

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember {
        mutableStateOf(
            when (label) {
                type -> dice.diceType.title
                borderColor -> dice.diceBorderColor.title
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
            onValueChange = { selectedText = it },
            readOnly = false,
            enabled = false,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primary,
                unfocusedBorderColor = MaterialTheme.colors.primary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(label) },
            trailingIcon = {
                Icon(icon, contentDescription = stringResource(R.string.show_options),
                    Modifier.clickable { expanded = !expanded })
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() })
        ) {
            listOfItems.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                    onDiceSettingsChanged(selectedText)
                }) {
                    Text(text = label)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DrawerBodyPreview() {
    DrawerBody(
        diceList = listOf(D6(), D6(), D6(), D6()),
        onRemoveDiceClicked = {},
        onDiceTypeChanged = { _, _ ->  },
        onDiceBorderColorChanged = { _, _ ->  },
        onDiceDotColorChanged = { _, _ ->  }
    )
}