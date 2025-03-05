package com.example.ecommerceapp.presentation.view.ui

import android.annotation.SuppressLint
import android.text.TextPaint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.ecommerceapp.presentation.viewModel.BottomNavScreenViewModel
import com.example.ecommerceapp.ui.theme.errorContainerDarkHighContrast
import androidx.compose.material3.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.TextUnit

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(errorContainerDarkHighContrast),
        contentAlignment = Alignment.Center
    ) {
        customListView()
    }
}

@Composable
fun HighlightBar() {
    Text(
        text = "PROFILE SCREEN",
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textAlign = TextAlign.Center,
        fontSize = 30.sp
    )
}

@SuppressLint("ResourceAsColor")
@Composable
fun titleWithIndicationBar(text: String,fontSize: TextUnit,letterCount:Int) {
    Column() {
        val charCount = text.take(letterCount)
        val fontSizeInPixels = with(LocalDensity.current) { fontSize.toPx() }

        val textPaint = TextPaint().apply {
            textSize = fontSizeInPixels
        }
        val widthOfFirst4Chars = textPaint.measureText(charCount)

        Canvas(modifier = Modifier) {
            val paints = Paint().apply {
                color = Color.Red
                strokeWidth = 10f
                isAntiAlias = true
            }
               drawIntoCanvas { canvas ->
                canvas.drawLine(
                    Offset(0f, 0f),
                    Offset(widthOfFirst4Chars, 0f),
                    paints
                )
            }
        }
        // TextView
        Text(
            text = text,
            fontSize = fontSize
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun customListView() {

    var bottomNavScreenViewModel: BottomNavScreenViewModel = hiltViewModel()

    var shortList = bottomNavScreenViewModel.prepareImageList()
    Column {
        titleWithIndicationBar("Profile",25.sp,4)
        LazyRow {
            itemsIndexed(shortList) { index, item ->
                Card(
                    elevation = 20.dp,
                    modifier = Modifier
                        .padding(8.dp)
                        .width(160.dp)
                        .height(300.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black),

                        ) {
                        val painter = rememberAsyncImagePainter(item.url)

                        Image(
                            painter = painter,
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .align(Alignment.BottomStart)
                        ) {
                            Text(
                                text = shortList[index].text,
                                modifier = Modifier
                                    .padding(4.dp),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                color = Color.White
                            )

                            Row(
                                modifier = Modifier
                                    .padding(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                    contentDescription = ""
                                )
                                Text(
                                    text = shortList[index].time,
                                    modifier = Modifier
                                        .padding(4.dp),
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileScreen()
}
