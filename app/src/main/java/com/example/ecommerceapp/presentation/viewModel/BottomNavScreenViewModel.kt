package com.example.ecommerceapp.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.ecommerceapp.Utils.CommonUtils
import com.example.ecommerceapp.data.model.ShortsDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BottomNavScreenViewModel @Inject constructor() : ViewModel() {
@Inject lateinit var commonUtils: CommonUtils

    fun prepareImageList(): List<ShortsDto> {
        return arrayListOf(
            ShortsDto("This is Card DemoThis is Card DemoThis is Card Demo",commonUtils.getCurrentTime(), "https://picsum.photos/400/300"),
            ShortsDto("This is Card Demo",commonUtils.getCurrentTime(), "https://picsum.photos/400/300"),
            ShortsDto("This is Card Demo",commonUtils.getCurrentTime(), "https://picsum.photos/400/300"),
            ShortsDto("This is Card Demo",commonUtils.getCurrentTime(), "https://picsum.photos/400/300")
        )
    }
}