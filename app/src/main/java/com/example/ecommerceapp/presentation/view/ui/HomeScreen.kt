package com.example.ecommerceapp.presentation.view.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecommerceapp.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {
    val pagerState = PagerState(pageCount = 6)

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TabScreen(pagerState)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun viewPager(
    pagerState: PagerState
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(0.dp,10.dp,0.dp,0.dp),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            state = pagerState
        ) { page ->
            when (page) {
                0 -> SettingScreen()
                1 -> PhoneScreen()
                2 -> ProfileScreen()
                3 -> SettingScreen()
                4 -> PhoneScreen()
                5 -> ProfileScreen()
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabScreen(pagerState: PagerState) {
    val coroutineScope = rememberCoroutineScope()
    val tabs = listOf(stringResource(id = R.string.setting), stringResource(id = R.string.phone), stringResource(id = R.string.profile),stringResource(id = R.string.setting), stringResource(id = R.string.phone), stringResource(id = R.string.profile))

    Column(modifier = Modifier.fillMaxWidth()) {
        ScrollableTabRow(selectedTabIndex = pagerState.currentPage,
            edgePadding = 16.dp,
            divider = {},
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
        Divider(
            modifier = Modifier.padding(vertical = 6.dp),
            color = Color.Gray,
            thickness = 2.dp
        )
        viewPager(pagerState)
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen()
}