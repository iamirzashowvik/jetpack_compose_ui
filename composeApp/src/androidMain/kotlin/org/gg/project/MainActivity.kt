package org.gg.project

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.Icon
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
import androidx.compose.runtime.*
import androidx.compose.ui.text.style.TextAlign


val PrimaryColor: Color = Color(0xFF8B54FF)
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
    val isDark = false
    val milestones = listOf("Milestone 2","Milestone 3", "Milestone 4", "Milestone 5")
    var selectedMilestone by remember { mutableStateOf("Milestone 4") }
    Column(modifier = Modifier.background(if (isDark) Color(0xff020016) else Color(0xfff3f2f8)), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(16.dp))
        WebDevelopmentCourseCard(isDark)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            milestones.forEach { milestone ->
                MilestoneItem(
                    title = milestone,
                    isSelected = milestone == selectedMilestone,
                    onClick = { selectedMilestone = milestone },
                    isDark = isDark
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .border(
                1.dp,
                if (isDark) Color(0x1AFFFFFF) else Color(0x12000000),
                shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(if (isDark) Color(0x0DFFFFFF) else Color.White.copy(alpha = .7f)),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Milestone 04 - Tentative Outline",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(600),
                    color =if (isDark) Color(0xFFFFE8FF) else Color.Black,
                ), modifier = Modifier.padding(16.dp)
            )
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color =  if (isDark) Color(0x1AFFFFFF) else Color(0x12000000))
            LazyColumn {
                items(5) { index ->
                    ModuleItem(

                        isDark = isDark,
                        title = "Module-16: Assignment-03 60 Marks Deadline Till 01 February 11.59 PM 50...",
                        date = "25 Jan",
                        time = "8:30 PM, Friday", isReleased = false
                    )
                }
            }


        }
    }
}

@Composable
fun ModuleItem(isDark: Boolean,date:String,title: String,time:String, isReleased:Boolean){
    var isClicked = false
    val isJoinAvailable = false
    Column (modifier = Modifier
        .fillMaxWidth()
        .clickable { isClicked = !isClicked }
        .background(
            brush = if (isClicked)
                Brush.horizontalGradient(
               (if (isDark) listOf(
                    Color(0xff3f164e),
                    Color(0xff250553),

                    )else listOf(
                       Color(0xff8B54FF).copy(alpha = .1f),
                   Color(0xff8B54FF).copy(alpha = .1f),

                    )  )

            ) else Brush.horizontalGradient(
               (if (isDark) listOf(
                    Color.Transparent,
                    Color.Transparent,
                ) else listOf(
                   Color.White.copy(alpha = .7f),
                           Color.White.copy(alpha = .7f)
                ))
            )
        )

    ){
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = date,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    //                        fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(400),
                    color =if (isDark) Color(0x80FFFFFF) else Color(0x80000000)
                )
            )
            Spacer(modifier = Modifier.width(30.dp))
            Column {
                Text(
                    text =title ,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        //                            fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(400),
                        color = if (isClicked && isDark) Color(0xFFFFFFFF)
                        else if (!isClicked && isDark) Color.White
                        else if (!isClicked && !isDark ) Color.Black
                        else PrimaryColor

                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = time,
                        style = TextStyle(
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            //                                fontFamily = FontFamily(Font(R.font.inter)),
                            fontWeight = FontWeight(400),
                            color =if (isDark) Color(0x80FFFFFF) else Color(0x80000000)
                        )
                    )
                    Spacer(modifier = Modifier.width(30.dp))

                   if (isReleased) {
                       Box(
                           modifier = Modifier


                               .background(
                                   if (isDark) Color(0x1AFFFFFF) else Color(0x1A00FFB2),
                                   shape = RoundedCornerShape(14.dp)
                               )
                               .padding(start = 12.dp, top = 4.dp, end = 12.dp, bottom = 4.dp)
                       ) {
                           Text(
                               text = "Released",
                               style = TextStyle(
                                   fontSize = 12.sp,
                                   //                                fontFamily = FontFamily(Font(R.font.inter)),
                                   fontWeight = FontWeight(400),
                                   color = Color(0xFF54CF68),
                               ),
                           )
                       }
                   } else{
                       Box(
                           modifier = Modifier


                               .background(
                                   if (isDark) Color(0x1AFFB82E) else Color(0x1AFFB82E),
                                   shape = RoundedCornerShape(14.dp)
                               )
                               .padding(start = 12.dp, top = 4.dp, end = 12.dp, bottom = 4.dp)
                       ) {
                           Text(
                               text = "Upcoming",
                               style = TextStyle(
                                   fontSize = 14.sp,
                                   lineHeight = 20.sp,
//                                   fontFamily = FontFamily(Font(R.font.inter)),
                                   fontWeight = FontWeight(400),
                                   color = Color(0xFFF88323),
                               )
                           )
                       }
                   }
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (isClicked && isReleased && isJoinAvailable){
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp)
                        .background(color = Color(0xFFFF453A), shape = RoundedCornerShape(size = 40.dp))
                        .padding(start = 20.dp, end = 20.dp).clickable {  },
                        contentAlignment = Alignment.Center) {
                        Text(
                            text = "Join Now",
                            style = TextStyle(
                                fontSize = 14.sp,
                                //                        fontFamily = FontFamily(Font(R.font.inter)),
                                fontWeight = FontWeight(600),
                                color = Color(0xFFFFFFFF),
                            )
                        )
                    }
                }

            }
        }
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp, color =  if (isDark) Color(0x1AFFFFFF) else Color(0x12000000))

    }
}


@Composable
fun MilestoneItem(title: String, isSelected: Boolean, onClick: () -> Unit,isDark: Boolean) {


    Box(
        modifier = Modifier
            .width(110.dp)
            .height(65.dp)
            .background(
                if (isDark) Color.Transparent else Color.White.copy(alpha = .7f),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                1.dp, brush = Brush.horizontalGradient(
                    if (isSelected) listOf(Color(0xFFE855DE), Color(0xFF5400EE)) else
                        listOf(Color(0x1AFFFFFF), Color(0x1AFFFFFF))

                ), shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .background(Color.Red))
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(500),
                    color = if (isDark)(if (isSelected) Color.White else Color(0xFF81808A)) else (if (isSelected) PrimaryColor   else Color(0xFF81808A)),
                )
            )

        }
    }
}


@Composable
fun WebDevelopmentCourseCard(isDark:Boolean) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
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
                            .background(
                                if (isDark) Color(0x1AFFFFFF) else Color(0x1A00FFB2),
                                shape = RoundedCornerShape(14.dp)
                            )
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
                            .background(
                                if (isDark) Color(0x1AFFB82E) else Color(0x1AFFB82E),
                                shape = RoundedCornerShape(14.dp)
                            )
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