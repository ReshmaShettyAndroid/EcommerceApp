package com.example.ecommerceapp.presentation.view.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.presentation.view.BottomBarScreen
import com.example.ecommerceapp.ui.theme.Purple40
import com.example.ecommerceapp.ui.theme.onPrimaryLight
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun ViewSliderScreen(navController: NavHostController) {
    viewSliderUi()
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun viewSliderUi() {
//    val selectedIndex = remember { mutableStateOf(0) }
    val screenList = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Phone,
        BottomBarScreen.Profile
    )
    val selectedItemData = remember { mutableStateOf(screenList[0]) }

    Scaffold(
        topBar = {centralizedTopToolbar()
//            topToolbar()
                 },
        bottomBar = {
            BottomNavigationlBar(screenList,selectedItemData)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            mainUi(selectedItemData)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topToolbar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Purple40,
            titleContentColor = onPrimaryLight,
        ),
        title = {
            Text(stringResource(id = R.string.news_app))
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun centralizedTopToolbar(){
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Purple40,
                titleContentColor = onPrimaryLight,
            ),
            title = {
                Text(
                    "News App",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            },
            actions = {
                IconButton(onClick = { /* do something */ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = ""
                    )
                }
            },
//            scrollBehavior = scrollBehavior,
        )

}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun mainUi(pagerIndex: MutableState<BottomBarScreen>)
{
Box(
modifier = Modifier.fillMaxWidth(),
contentAlignment = Alignment.Center
) {
    Column(){
            when (pagerIndex.value) {
                BottomBarScreen.Home -> HomeScreen()
                BottomBarScreen.Phone -> PhoneScreen()
                BottomBarScreen.Profile -> ProfileScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationlBar(screenList:List<BottomBarScreen>,selectedItemData:MutableState<BottomBarScreen>) {
    val selectedItem = selectedItemData.value

    BottomNavigation(elevation = 10.dp) {
        screenList.forEach { item->
            BottomNavigationItem(icon = {
                Icon(
                    imageVector = item.icon,
                    item.description,
                    tint = if (item == selectedItem) Color.White else Color.Gray
                )
            },
                label = { Text(text = item.title) },
                selected = selectedItemData.value == item,
                onClick = {
                    selectedItemData.value = item
                })
        }
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun mainUiDemo(pagerIndex: MutableState<Int>)
{
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(){
            when (pagerIndex.value) {
                0 -> HomeScreen()
                1 -> PhoneScreen()
                2 -> ProfileScreen()
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomNavigationlBarDemo(selectedIndex: MutableState<Int>) {
    val selectedItem = selectedIndex.value

    BottomNavigation(elevation = 10.dp) {

        BottomNavigationItem(icon = {
            Icon(
                imageVector = Icons.Default.Home,
                "",
                tint = if (selectedItem == 0) Color.White else Color.Gray
            )
        },
            label = { Text(text = stringResource(id = R.string.home)) },
            selected = selectedIndex.value == 0,
            onClick = {
                selectedIndex.value = 0
            })

        BottomNavigationItem(icon = {
            Icon(
                imageVector = Icons.Default.Phone,
                "",
                tint = if (selectedItem == 1) Color.White else Color.Gray
            )
        },
            label = { Text(text = stringResource(id = R.string.phone)) },
            selected = (selectedIndex.value == 1),
            onClick = {
                selectedIndex.value = 1
            })

        BottomNavigationItem(icon = {
            Icon(
                imageVector = Icons.Default.Person,
                "",
                tint = if (selectedItem == 2) Color.White else Color.Gray
            )
        },
            label = { Text(text = stringResource(id = R.string.profile)) },
            selected = (selectedIndex.value == 2),
            onClick = {
                selectedIndex.value = 2
            })
    }
}

