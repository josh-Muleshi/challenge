package cd.wayupdev.challenger.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cd.wayupdev.challenger.R
import cd.wayupdev.challenger.ui.theme.Black_ic

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()){
        Image(
            painter = painterResource(id = R.drawable.ic_splash2),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Black_ic
                        )
                    )
                )
                .height(300.dp)
                .fillMaxWidth()
                .align(Alignment.BottomStart),
            contentAlignment = Alignment.BottomStart
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Happy",
                    fontSize = 40.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Serif
                )
                Spacer(modifier = Modifier.padding(4.dp))
                Text(
                    text = "Birthday",
                    fontSize = 40.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Serif
                )
            }
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}