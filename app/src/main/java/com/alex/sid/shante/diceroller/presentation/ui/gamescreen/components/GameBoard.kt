package com.alex.sid.shante.diceroller.presentation.ui.gamescreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alex.sid.shante.diceroller.presentation.ui.gamescreen.GameViewModel

@Composable
fun GameBoard(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel(),
    onDiceClicked: (Int) -> Unit,
) {
    val gameState by gameViewModel.state.collectAsState()
    val diceList = gameState.diceList

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center,
            columns = GridCells.Fixed(6)
        ) {
            itemsIndexed(
                diceList,
                span = { index, _ ->
                    val span = calculateSpan(diceList.size, index)
                    GridItemSpan(span)
                }
            ) { index, dice ->
                Box(
                    modifier = modifier
                        .size(LocalConfiguration.current.screenWidthDp.dp / 3)
                        .clickable { onDiceClicked(index) },
                    contentAlignment = Alignment.Center
                ) {
                    DiceWithImage(
                        dice = diceList[index]
                    )
                }
            }
        }
    }
}

private fun calculateSpan(itemsListSize: Int, indexOfElement: Int): Int {
    return when (itemsListSize) {
        1 -> 6
        in 1..3 -> {
            when (indexOfElement) {
                in 0..1 -> 3
                else -> 6
            }
        }
        4 -> 3
        else -> {
            val diceleft = itemsListSize % 3
            if (indexOfElement in 0..itemsListSize - 1 - diceleft) 2
            else when (diceleft) {
                1 -> 6
                2 -> 3
                else -> 1
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameBoardWithButtonPreview() {
    GameBoard() {}
}