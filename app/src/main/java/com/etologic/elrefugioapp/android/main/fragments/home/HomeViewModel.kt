package com.etologic.elrefugioapp.android.main.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.etologic.elrefugioapp.android.global.base.BaseViewModel

class HomeViewModel : BaseViewModel() {
    
    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}