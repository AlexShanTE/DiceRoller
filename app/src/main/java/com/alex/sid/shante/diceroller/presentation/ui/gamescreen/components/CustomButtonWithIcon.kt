package com.alex.sid.shante.diceroller.presentation.ui.gamescreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomButtonWithIcon(
    modifier: Modifier = Modifier,
    canvasSize: Dp = 400.dp
) {
    Box(
        modifier
            .size(canvasSize)
            .drawBehind {
                buttonBoard(componentSize = size * 0.75f)
            }
        ,
        contentAlignment = Alignment.Center
    ) {
        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(canvasSize * 0.7f) //avoid the oval shape
            ,
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.Blue),
            contentPadding = PaddingValues(0.dp),  //avoid the little icon
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "content description"
            )
        }
    }
}

fun DrawScope.buttonBoard(
    componentSize: Size,
) {
    drawRect(
        color = Color.Black,
        topLeft = Offset(0f, componentSize.height * 0.175f),
        size = Size(componentSize.width * 0.75f, componentSize.height)
    )
    drawCircle(
        color = Color.Black,
        radius = componentSize.height / 2,
        center = Offset(componentSize.width * 0.75f, componentSize.height * 0.675f),
    )
}

@Preview(showBackground = true)
@Composable
fun CustomButtonPreview() {
    CustomButtonWithIcon()
}