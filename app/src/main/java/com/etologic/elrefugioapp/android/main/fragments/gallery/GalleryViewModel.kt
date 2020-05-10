package com.etologic.elrefugioapp.android.main.fragments.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.etologic.elrefugioapp.android.global.base.BaseViewModel

class GalleryViewModel : BaseViewModel() {
    
    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text
}