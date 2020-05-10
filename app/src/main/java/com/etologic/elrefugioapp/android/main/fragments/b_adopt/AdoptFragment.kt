package com.etologic.elrefugioapp.android.main.fragments.b_adopt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.etologic.elrefugioapp.R.layout
import com.etologic.elrefugioapp.android.main.activity.MainBaseFragment
import javax.inject.Inject

class AdoptFragment : MainBaseFragment() {
    
    @Inject
    lateinit var viewModelFactory: AdoptViewModelFactory
    private lateinit var viewModel: AdoptViewModel
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout.b_adopt_fragment, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AdoptViewModel::class.java)
        viewModel.text.observe(viewLifecycleOwner, Observer { })
    }
}
