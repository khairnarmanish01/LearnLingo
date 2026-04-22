package com.example.learnlingo.presentation.components

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class ChatBubbleShape(
    private val cornerRadius: Dp = 16.dp,
    private val tailWidth: Dp = 10.dp,
    private val tailHeight: Dp = 16.dp
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val r = with(density) { cornerRadius.toPx() }
        val tW = with(density) { tailWidth.toPx() }
        val tH = with(density) { tailHeight.toPx() }

        // Dynamically calculate the exact vertical center of the composable
        val tailCenterY = size.height / 2f
        val tailTopY = tailCenterY - (tH / 2f)
        val tailBottomY = tailCenterY + (tH / 2f)

        val path = Path().apply {
            // Top-Left Arc
            arcTo(
                rect = Rect(left = tW, top = 0f, right = tW + 2 * r, bottom = 2 * r),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // Top-Right Arc
            arcTo(
                rect = Rect(
                    left = size.width - 2 * r,
                    top = 0f,
                    right = size.width,
                    bottom = 2 * r
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // Bottom-Right Arc
            arcTo(
                rect = Rect(
                    left = size.width - 2 * r,
                    top = size.height - 2 * r,
                    right = size.width,
                    bottom = size.height
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // Bottom-Left Arc
            arcTo(
                rect = Rect(
                    left = tW,
                    top = size.height - 2 * r,
                    right = tW + 2 * r,
                    bottom = size.height
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            // Draw up the left side to the dynamically centered bottom of the tail
            lineTo(tW, tailBottomY)

            // Draw the tail pointing outwards, perfectly centered
            lineTo(0f, tailCenterY)  // Tip of the tail
            lineTo(tW, tailTopY)     // Top base of the tail

            // Close the path back to the start of the Top-Left Arc
            close()
        }

        return Outline.Generic(path)
    }
}