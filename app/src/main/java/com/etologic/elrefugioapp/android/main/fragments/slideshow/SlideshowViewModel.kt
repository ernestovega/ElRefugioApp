package com.etologic.elrefugioapp.android.main.fragments.slideshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.etologic.elrefugioapp.android.global.base.BaseViewModel

class SlideshowViewModel : BaseViewModel() {
    
    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text
}