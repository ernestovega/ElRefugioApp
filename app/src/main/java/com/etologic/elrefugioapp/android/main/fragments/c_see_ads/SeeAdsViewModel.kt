package com.etologic.elrefugioapp.android.main.fragments.c_see_ads

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.etologic.elrefugioapp.android.global.base.BaseViewModel

class SeeAdsViewModel : BaseViewModel() {
    
    private val _text = MutableLiveData<String>().apply {
        value = "This is see ads Fragment"
    }
    val text: LiveData<String> = _text
}