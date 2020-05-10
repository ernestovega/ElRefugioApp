package com.etologic.elrefugioapp.android.main.fragments.c_see_ads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class SeeAdsViewModelFactory
@Inject constructor() : ViewModelProvider.NewInstanceFactory() {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SeeAdsViewModel() as T
    }
}