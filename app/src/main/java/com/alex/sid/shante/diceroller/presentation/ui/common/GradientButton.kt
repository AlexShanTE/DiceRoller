package com.alex.sid.shante.diceroller.presentation.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonElevation
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProvideTextStyle
import androidx.compose.material.Surface
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GradientButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    gradient: Brush = Brush.horizontalGradient(
        listOf(
            MaterialTheme.colors.primary,
            MaterialTheme.colors.primaryVariant
        )
    ),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    contentColor: Color = MaterialTheme.colors.onPrimary,
    disabledContentColor: Color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
    disabledBackgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
        .compositeOver(MaterialTheme.colors.surface),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {
    val actualElevation = elevation?.elevation(enabled, interactionSource)?.value ?: 0.dp
    val actualContentColor = if (enabled) contentColor else disabledContentColor
    val background = if (enabled) {
        Modifier.background(brush = gradient)
    } else {
        Modifier.background(color = disabledBackgroundColor)
    }

    rememberRipple()

    Surface(
        onClick = onClick,
        modifier = modifier
            .shadow(actualElevation, shape, true)
            .then(background),
        enabled = enabled,
        shape = shape,
        color = Color.Transparent,
        contentColor = actualContentColor.copy(alpha = 1f),
        border = border,
        elevation = 0.dp,
        interactionSource = interactionSource
    ) {
        CompositionLocalProvider(LocalContentAlpha provides actualContentColor.alpha) {
            ProvideTextStyle(
                value = MaterialTheme.typography.button
            ) {
                Row(
                    Modifier
                        .defaultMinSize(
                            minWidth = ButtonDefaults.MinWidth,
                            minHeight = ButtonDefaults.MinHeight
                        )
                        .padding(contentPadding),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    content = content
                )
            }
        }
    }
}