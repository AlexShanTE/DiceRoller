package com.alex.sid.shante.diceroller.presentation.ui.gamescreen.diceSettingsDrawer.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.alex.sid.shante.diceroller.R
import com.alex.sid.shante.diceroller.domain.models.Dice
import com.alex.sid.shante.diceroller.ui.theme.minionVariableConcept

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
            label = {
                Text(
                    text = label,
                    fontFamily = minionVariableConcept,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            },
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