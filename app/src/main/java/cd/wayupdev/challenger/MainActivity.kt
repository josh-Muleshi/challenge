package cd.wayupdev.challenger

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import cd.wayupdev.challenger.ui.theme.ChallengerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengerTheme {
                // A surface container using the 'background' color from the theme
                Column {
                    /*var progress by remember {
                        mutableStateOf(0f)
                    }*/

                    /*Slider(
                        value = progress,
                        onValueChange = {
                            progress = it
                        },
                        modifier = Modifier.padding(horizontal = 32.dp)
                    )*/
                    SettingItem{ item ->
                        when(item){
                            0 -> Toast.makeText(this@MainActivity, item, Toast.LENGTH_SHORT).show()
                            2 -> Toast.makeText(this@MainActivity, item, Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        }
    }
}

@OptIn(ExperimentalMotionApi::class)
@Composable
fun ProfileHeader(progress: Float) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxWidth()

    ) {
        val properties = motionProperties(id = "profile_pic")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .layoutId("box")
        )
        Image(
            painter = painterResource(id = R.drawable.picture),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = properties.value.color("background"),
                    shape = CircleShape
                )
                .layoutId("profile_pic")
        )
        Text(
            text = "Philipp Lackner",
            fontSize = 24.sp,
            modifier = Modifier.layoutId("username"),
            color = properties.value.color("background")
        )
    }
}

@OptIn(ExperimentalMaterialApi::class, androidx.compose.foundation.ExperimentalFoundationApi::class)
@Composable
fun SettingItem(selectedItem: (Int)->Unit) {
    var progress by remember {
        mutableStateOf(0f)
    }
    val listState = rememberLazyListState()

    val motionProgress by animateFloatAsState(targetValue = listState.firstVisibleItemIndex.toFloat()) {
        (it + 1)/listState.layoutInfo.totalItemsCount
    }
    val names : List<String> = listOf("Language",
        "Dark Mode", "About", "Help",
        "Dark Mode", "About", "Help",
        "Dark Mode", "About", "Help",
        "Dark Mode", "About", "Help",
        "Dark Mode", "About", "Help",
        "Dark Mode", "About", "Help",)
    LazyColumn(state = listState,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.wrapContentHeight(),
        content = {
        stickyHeader {
            ProfileHeader(progress = progress)
        }
        itemsIndexed(names){ index, name ->
            Row(
                modifier = Modifier.fillMaxWidth().clickable { selectedItem(index) },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = name, modifier = Modifier.padding(16.dp), color = Color.Black)
            }
            Divider()
        }
    })
}

