package com.alex.sid.shante.diceroller.presentation.ui.gamescreen.diceSettingsDrawer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
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