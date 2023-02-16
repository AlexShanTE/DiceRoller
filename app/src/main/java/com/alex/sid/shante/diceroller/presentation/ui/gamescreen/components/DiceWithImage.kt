package com.alex.sid.shante.diceroller.presentation.ui.gamescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alex.sid.shante.diceroller.R
import com.alex.sid.shante.diceroller.domain.models.D6
import com.alex.sid.shante.diceroller.domain.models.Dice

@Composable
fun DiceWithImage(
    dice: Dice,
    modifier: Modifier = Modifier,
) {
//    var result by remember {
//        mutableStateOf(1)
//    }

    val imageResource = dice.imageList[dice.currentValue]

    Image(
        painter = painterResource(imageResource),
        contentDescription = stringResource(R.string.image),
//        modifier = Modifier.clickable(
//            enabled = true,
//            onClick = {
//                result = (0 until dice.imageList.size).random()
//            })
    )

//        Button(
//            onClick = { result = (0 until dice.imageList.size).random() },
//            colors = ButtonDefaults.textButtonColors(backgroundColor = Color.Black)
//        ) {
//            Text(
//                stringResource(id = R.string.roll),
//                color = Color.White
//            )
//        }
}


@Preview(showBackground = true)
@Composable
fun DiceWithImagePreview() {
    DiceWithImage(D6())
}