package com.alex.sid.shante.diceroller.presentation.ui.gamescreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alex.sid.shante.diceroller.R
import com.alex.sid.shante.diceroller.presentation.ui.gamescreen.components.GameBoard

@Composable
fun GameScreen(
    modifier: Modifier = Modifier,
    gameViewModel: GameViewModel = viewModel()
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        GameBoard() { diceIndex ->
            gameViewModel.rollDice(diceIndex)
        }
        Spacer(modifier = modifier.height(80.dp))
        Button(
            modifier = modifier
                .width(150.dp)
                .height(50.dp),
            onClick = { gameViewModel.rollThemAll() },
            colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Black)
        ) {
            Text(
                stringResource(id = R.string.roll),
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    GameScreen()
}