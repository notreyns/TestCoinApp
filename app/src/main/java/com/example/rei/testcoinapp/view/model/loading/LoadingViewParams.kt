package com.example.cointestapp.view.model.loading

import androidx.annotation.StringRes
import com.example.rei.testcoinapp.R

data class LoadingViewParams(
    val isVisible: Boolean,
    @StringRes val text: Int = R.string.progress_bar_status
)