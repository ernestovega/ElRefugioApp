package com.etologic.elrefugioapp.android.main.fragments.b_adopt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.etologic.elrefugioapp.android.global.base.BaseViewModel

class AdoptViewModel : BaseViewModel() {
    
    private val _text = MutableLiveData<String>().apply {
        value = "This is adopt Fragment"
    }
    val text: LiveData<String> = _text
}