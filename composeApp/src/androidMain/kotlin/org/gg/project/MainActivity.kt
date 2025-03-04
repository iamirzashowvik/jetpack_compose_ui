package org.gg.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
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

@Preview()
@Composable
fun AppAndroidPreview() {
    val isDark= true
    Column(modifier = Modifier.background(if (isDark) Color(0xff020016) else Color(0xfff3f2f8)), horizontalAlignment = Alignment.CenterHorizontally) {
        WebDevelopmentCourseCard(isDark)
    }

}

@Composable
fun WebDevelopmentCourseCard(isDark:Boolean) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(
                width = 1.dp,
                color = if (isDark) Color(0x00FFFFFF) else Color(0x00FFFFFF),
                shape = RoundedCornerShape(size = 12.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .background(if (isDark) Color(0x0DFFFFFF) else Color.White.copy(alpha = .7f)),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left section: Batch, Level, and Course Title
            Column(modifier = Modifier.weight(1f)) {
                // Batch and Level labels
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Batch label (Green background)
                    Box(
                        modifier = Modifier
                            .background(if (isDark) Color(0x1AFFFFFF) else Color(0x1A00FFB2), shape = RoundedCornerShape(14.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Batch - 10",
                            style = TextStyle(
                                fontSize = 14.sp,
//                                fontFamily = FontFamily(Font(R.font.inter)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF00C086),
                            )
                        )
                    }

                    // Level label (Orange background)
                    Box(
                        modifier = Modifier
                            .background(if (isDark) Color(0x1AFFB82E) else Color(0x1AFFB82E), shape = RoundedCornerShape(14.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)

                    ) {
                        Text(
                            text = "Level - 1",
                            style = TextStyle(
                                fontSize = 14.sp,
//                                fontFamily = FontFamily(Font(R.font.inter)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFFF88323),
                            )
                        )
                    }
                }

                // Course Title
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Complete Web Development Course Outline by Programming Hero",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
//                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(500),
                        color =if (isDark) Color.White else Color(0xFF000000),
                    )
                )
            }

            Spacer(modifier = Modifier.width(10.dp))
            // Right section: Calendar Icon
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFFF6B6B), Color(0xFF4ECDC4))
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                ,
                contentAlignment = Alignment.Center
            ) {
            }
        }
    }
}