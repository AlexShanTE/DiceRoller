package com.alex.sid.shante.diceroller.presentation.ui.gamescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alex.sid.shante.diceroller.R
import com.alex.sid.shante.diceroller.domain.models.Dice
import com.alex.sid.shante.diceroller.domain.models.DiceSettings
import com.alex.sid.shante.diceroller.presentation.ui.common.GradientButton
import com.alex.sid.shante.diceroller.presentation.ui.gamescreen.components.GameBoard
import com.alex.sid.shante.diceroller.presentation.ui.gamescreen.drawer.DrawerBody
import com.alex.sid.shante.diceroller.presentation.ui.gamescreen.drawer.DrawerHeader
import com.alex.sid.shante.diceroller.ui.theme.minionVariableConcept
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel()
) {
    val gameState by gameViewModel.state.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val drawerState = rememberDrawerState(DrawerValue.Closed,
        confirmStateChange = {
            if (it == DrawerValue.Closed) {
                keyboardController?.hide()
                focusManager.clearFocus()
            }
            true
        }
    )

    val scope = rememberCoroutineScope()
    val diceSettings = DiceSettings()

    ModalDrawer(
        modifier = modifier.clickable {
            keyboardController?.hide()
            focusManager.clearFocus()
        },
        drawerState = drawerState,
        drawerElevation = 8.dp,
        drawerContent = {
            Column(modifier = modifier.fillMaxWidth()) {
                DrawerHeader(
                    modifier = modifier.height(48.dp),
                    text = stringResource(
                        R.string.dice_settings
                    )
                )
                Box(
                    modifier = modifier.weight(1f)
                ) {
                    DrawerBody(
                        modifier = modifier,
                        diceList = gameState.diceList,
                        onRemoveDiceClicked = { index -> gameViewModel.removeDice(index) },
                        onDiceTypeChanged = { index, type ->
                            val newDice =
                                diceSettings.diceList().find { it.diceType == type } ?: Dice.D6dots()
                            val oldDice = gameState.diceList[index]
                            gameViewModel.editDice(
                                index, newDice.makeCopy(
                                    diceType = newDice.diceType,
                                    currentValue = 1,
                                    maxValue = newDice.maxValue,
                                    diceColor = oldDice.diceColor,
                                    diceEdgeColor = oldDice.diceEdgeColor,
                                    diceDotColor = oldDice.diceDotColor,
                                    imageList = newDice.imageList
                                )
                            )
                        },
                        onDiceMaxValueChanged = { index, value ->
                            val dice = gameState.diceList[index]
                            if (dice is Dice.Custom) {
                                gameViewModel.editDice(
                                    index,
                                    dice.makeCopy(
                                        diceType = dice.diceType,
                                        currentValue = 1,
                                        maxValue = value,
                                        diceColor = dice.diceColor,
                                        diceEdgeColor = dice.diceEdgeColor,
                                        diceDotColor = dice.diceDotColor,
                                        imageList = dice.imageList
                                    )
                                )
                            }
                        },
                        onDiceColorChanged = { index, diceColor ->
                            val dice = gameState.diceList[index]
                            gameViewModel.editDice(
                                index, dice.makeCopy(
                                    diceType = dice.diceType,
                                    currentValue = dice.currentValue,
                                    maxValue = dice.maxValue,
                                    diceColor = diceColor,
                                    diceEdgeColor = dice.diceEdgeColor,
                                    diceDotColor = dice.diceDotColor,
                                    imageList = dice.imageList
                                )
                            )
                        },
                        onDiceEdgeColorChanged = { index, diceEdgeColor ->
                            val dice = gameState.diceList[index]
                            gameViewModel.editDice(
                                index, dice.makeCopy(
                                    diceType = dice.diceType,
                                    currentValue = dice.currentValue,
                                    maxValue = dice.maxValue,
                                    diceColor = dice.diceColor,
                                    diceEdgeColor = diceEdgeColor,
                                    diceDotColor = dice.diceDotColor,
                                    imageList = dice.imageList
                                )
                            )
                        },
                        onDiceDotColorChanged = { index, dotColor ->
                            val dice = gameState.diceList[index]
                            gameViewModel.editDice(
                                index, dice.makeCopy(
                                    diceType = dice.diceType,
                                    currentValue = dice.currentValue,
                                    maxValue = dice.maxValue,
                                    diceColor = dice.diceColor,
                                    diceEdgeColor = dice.diceEdgeColor,
                                    diceDotColor = dotColor,
                                    imageList = dice.imageList
                                )
                            )
                        }
                    )
                }
                GradientButton(
                    modifier = modifier
                        .height(48.dp)
                        .fillMaxWidth(),
                    shape = RectangleShape,
                    elevation = ButtonDefaults.elevation(8.dp),
                    enabled = gameState.diceList.size < 9,
                    onClick = { gameViewModel.addDice() }
                ) {
                    Text(
                        text = stringResource(id = R.string.add_dice),
                        fontFamily = minionVariableConcept,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp
                    )
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
                text = stringResource(id = R.string.total, gameState.total),
                fontFamily = minionVariableConcept,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
            )
            Spacer(modifier = modifier.height(40.dp))
            GameBoard() { diceIndex ->
                gameViewModel.rollDice(diceIndex)
            }
            Spacer(modifier = modifier.height(40.dp))
            GradientButton(
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
                Text(
                    text = stringResource(id = R.string.roll),
                    fontFamily = minionVariableConcept,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
            }
            Spacer(modifier = modifier.height(20.dp))
            GradientButton(
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
                Text(
                    text = stringResource(id = R.string.setup_dice),
                    fontFamily = minionVariableConcept,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen()
}