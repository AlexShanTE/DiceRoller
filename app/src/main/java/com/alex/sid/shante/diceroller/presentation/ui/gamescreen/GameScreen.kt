package com.alex.sid.shante.diceroller.presentation.ui.gamescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.Text
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alex.sid.shante.diceroller.R
import com.alex.sid.shante.diceroller.data.diceList
import com.alex.sid.shante.diceroller.presentation.ui.gamescreen.components.GameBoard
import com.alex.sid.shante.diceroller.presentation.ui.gamescreen.drawer.DrawerBody
import com.alex.sid.shante.diceroller.presentation.ui.gamescreen.drawer.DrawerHeader
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel()
) {
    val gameState by gameViewModel.state.collectAsState()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerElevation = 8.dp,
        drawerContent = {
            Column(modifier = modifier.fillMaxWidth()) {
                DrawerHeader(
                    modifier = modifier.height(40.dp),
                    text = stringResource(R.string.dice_settings)
                )
                Box(
                    modifier = modifier.weight(1f)
                ) {
                    DrawerBody(
                        modifier = modifier,
                        diceList = gameState.diceList,
                        onRemoveDiceClicked = { index -> gameViewModel.removeDice(index)},
                        onDiceTypeChanged = {index, type ->
                            diceList.find { it.diceType == type }?.let { dice ->
                                gameViewModel.editDice(index, dice)
                            }
                        },
                        onDiceBorderColorChanged = {index, borderColor ->},
                        onDiceDotColorChanged = {index, dotColor ->}
                    )
                }
                Button(
                    modifier = modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    shape = RectangleShape,
                    elevation = ButtonDefaults.elevation(8.dp),
                    enabled = gameState.diceList.size < 9,
                    onClick = { gameViewModel.addDice() }
                ) {
                    Text(stringResource(id = R.string.add_dice))
                }
            }
        }
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                stringResource(id = R.string.total, gameState.total)
            )
            GameBoard() { diceIndex ->
                gameViewModel.rollDice(diceIndex)
            }
            Spacer(modifier = modifier.height(80.dp))
            Button(
                modifier = modifier
                    .width(150.dp)
                    .height(50.dp),
                elevation = ButtonDefaults.elevation(8.dp),
                enabled = gameState.diceList.isNotEmpty(),
                onClick = {
                    scope.launch {
                        gameViewModel.rollThemAll()
                    }
                },
            ) {
                Text(stringResource(id = R.string.roll))
            }
            Spacer(modifier = modifier.height(20.dp))
            Button(
                modifier = modifier
                    .width(150.dp)
                    .height(50.dp),
                elevation = ButtonDefaults.elevation(8.dp),
                onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }
            ) {
                Text(stringResource(id = R.string.setup_dice))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen()
}