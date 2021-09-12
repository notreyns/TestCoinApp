package com.example.cointestapp.view.model.fragment

import android.os.Bundle
import androidx.annotation.IdRes

data class FragmentTransaction(
    @IdRes val navigationId: Int,
    val bundle: Bundle? = null
)