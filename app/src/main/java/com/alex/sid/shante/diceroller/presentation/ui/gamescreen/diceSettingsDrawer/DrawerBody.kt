package com.alex.sid.shante.diceroller.presentation.ui.gamescreen.diceSettingsDrawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import com.alex.sid.shante.diceroller.domain.models.Dice
import com.alex.sid.shante.diceroller.domain.models.DiceColor
import com.alex.sid.shante.diceroller.domain.models.DiceDotColor
import com.alex.sid.shante.diceroller.domain.models.DiceEdgeColor
import com.alex.sid.shante.diceroller.domain.models.DiceType
import com.alex.sid.shante.diceroller.presentation.ui.gamescreen.diceSettingsDrawer.components.DiceSettingsCard

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
            DiceSettingsCard(
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