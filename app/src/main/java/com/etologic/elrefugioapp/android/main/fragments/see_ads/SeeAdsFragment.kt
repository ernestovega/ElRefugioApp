package com.etologic.elrefugioapp.android.main.fragments.see_ads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.etologic.elrefugioapp.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SeeAdsFragment : DaggerFragment() {
    
    @Inject
    lateinit var viewModelFactory: SeeAdsViewModelFactory
    private lateinit var viewModel: SeeAdsViewModel
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.c_see_ads_fragment, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SeeAdsViewModel::class.java)
        viewModel.text.observe(viewLifecycleOwner, Observer { })
    }
}
