package org.gg.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppAndroidPreview()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    TimeLineView()


}

@Composable
fun TimelineNode(
    position: TimelineNodePosition,
    circleParameters: CircleParameters,
    lineParameters: LineParameters? = null,
    contentStartOffset: Dp = 16.dp,
    spacer: Dp = 32.dp,
    content: @Composable BoxScope.(modifier: Modifier) -> Unit
) {
    val iconPainter = circleParameters.icon?.let { painterResource(id = it) }
    Box(
        modifier = Modifier
            .wrapContentSize()
            .drawBehind {
                val circleRadiusInPx = circleParameters.radius.toPx()

                lineParameters?.let {
                    val dashLength = 20f  // Height of each dash
                    val gapLength = 20f   // Gap between dashes
                    var startY = circleRadiusInPx * 2 // Start position of the first dash

                    while (startY < this.size.height) {
                        drawLine(
                            brush = it.brush,
                            start = Offset(x = circleRadiusInPx, y = startY),
                            end = Offset(x = circleRadiusInPx, y = (startY + dashLength).coerceAtMost(this.size.height)),
                            strokeWidth = it.strokeWidth.toPx()
                        )
                        startY += dashLength + gapLength  // Move down by dash + gap
                    }
                }


                drawCircle(
                    circleParameters.backgroundColor,
                    circleRadiusInPx,
                    center = Offset(x = circleRadiusInPx, y = circleRadiusInPx)
                )

                circleParameters.stroke?.let { stroke ->
                    val strokeWidthInPx = stroke.width.toPx()
                    drawCircle(
                        color = stroke.color,
                        radius = circleRadiusInPx - strokeWidthInPx / 2,
                        center = Offset(x = circleRadiusInPx, y = circleRadiusInPx),
                        style = Stroke(width = strokeWidthInPx)
                    )
                }

                iconPainter?.let { painter ->
                    this.withTransform(
                        transformBlock = {
                            translate(
                                left = circleRadiusInPx - painter.intrinsicSize.width / 2f,
                                top = circleRadiusInPx - painter.intrinsicSize.height / 2f
                            )
                        },
                        drawBlock = {
                            this.drawIntoCanvas {
                                with(painter) {
                                    draw(intrinsicSize)
                                }
                            }
                        })
                }
            }
    ) {
        content(
            Modifier
                .defaultMinSize(minHeight = circleParameters.radius * 2)
                .padding(
                    start = circleParameters.radius * 2 + contentStartOffset,
                    bottom = if (position != TimelineNodePosition.LAST) spacer else 0.dp
                )
        )
    }
}

@Composable
private fun MessageBubble(modifier: Modifier, containerColor: Color) {
    Card(
        modifier = modifier
            .width(200.dp)
            .height(45.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, containerColor)
    ) {}
}




@Composable
private fun TimeLineView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val LightBlue = Color.Blue
        val Purple = Color.Cyan
        TimelineNode(
            position = TimelineNodePosition.FIRST,
            circleParameters = CircleParametersDefaults.circleParameters(
                radius = 5.dp,
                backgroundColor = Color(0xFFC5C0DB)
            ),
            lineParameters = LineParametersDefaults.linearGradient(
                startColor = Color(0x26000000),
                endColor = Color(0x26000000)
            ),
        ) { modifier ->
            Column(modifier = modifier) {
                Text(
                    text = "Just hop onto the Play Store and search for Neptune by Programming Hero. Once you find it, scroll down a bit and hit the 'Join Beta' button. Easy & Simple!",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 20.8.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFFFFFFFF),
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(R.drawable.image24),
                    contentDescription = "My Image"
                )

            }
        }

        val LightCoral= Color.Green
        TimelineNode(
            position = TimelineNodePosition.MIDDLE,
            circleParameters = CircleParametersDefaults.circleParameters(
                backgroundColor = Purple
            ),
            lineParameters = LineParametersDefaults.linearGradient(
                startColor = Purple,
                endColor = LightCoral
            ),
            contentStartOffset = 16.dp
        ) { modifier -> MessageBubble(modifier, containerColor = Purple) }

        TimelineNode(
            position = TimelineNodePosition.LAST,
            circleParameters = CircleParametersDefaults.circleParameters(
                backgroundColor = LightCoral,
                stroke = StrokeParameters(color =Color.Yellow, width = 2.dp),
//                icon = R.drawable.ic_bubble_warning_16
            ),
            lineParameters = LineParametersDefaults.linearGradient(
                startColor = LightCoral,
                endColor = Purple
            ),
        ) { modifier -> MessageBubble(modifier, containerColor = Color.Yellow) }
    }
}

data class CircleParameters(
    val radius: Dp,
    val backgroundColor: Color,
    val stroke: StrokeParameters? = null,
    @DrawableRes val icon: Int? = null
)

data class LineParameters(val strokeWidth: Dp, val brush: Brush)

data class StrokeParameters(val color: Color, val width: Dp)

enum class TimelineNodePosition {
    FIRST,
    MIDDLE,
    LAST
}


object CircleParametersDefaults {

    private val defaultCircleRadius = 12.dp

    fun circleParameters(
        radius: Dp = defaultCircleRadius,
        backgroundColor: Color = Color.Cyan,
        stroke: StrokeParameters? = null,
        @DrawableRes icon: Int? = null
    ) = CircleParameters(radius, backgroundColor, stroke, icon)
}


object MessageBubbleParametersDefaults {

    private val defaultCircleRadius = 12.dp

    fun circleParameters(
        radius: Dp = defaultCircleRadius,
        backgroundColor: Color = Color.Cyan,
        stroke: StrokeParameters? = null,
        @DrawableRes icon: Int? = null
    ) = CircleParameters(radius, backgroundColor, stroke, icon)
}


object LineParametersDefaults {

    private val defaultLinearGradient = 3.dp

    fun linearGradient(
        strokeWidth: Dp = defaultLinearGradient,
        startColor: Color,
        endColor: Color,
        startY: Float = 0.0f,
        endY: Float = Float.POSITIVE_INFINITY
    ): LineParameters {
        val brush = Brush.verticalGradient(
            colors = listOf(startColor, endColor),
            startY = startY,
            endY = endY
        )
        return LineParameters(strokeWidth, brush)
    }
}