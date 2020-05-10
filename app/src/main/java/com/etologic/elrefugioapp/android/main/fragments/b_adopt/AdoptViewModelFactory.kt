package com.etologic.elrefugioapp.android.main.fragments.b_adopt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class AdoptViewModelFactory
@Inject constructor() : ViewModelProvider.NewInstanceFactory() {
    
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AdoptViewModel() as T
    }
}