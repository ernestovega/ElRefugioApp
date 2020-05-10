package com.etologic.elrefugioapp.android.main.fragments.b_adopt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.etologic.elrefugioapp.R
import dagger.android.support.DaggerAppCompatDialogFragment
import javax.inject.Inject

class AdoptDetailDialogFragment : DaggerAppCompatDialogFragment() {
    
    @Inject
    lateinit var viewModelFactory: AdoptViewModelFactory
    private lateinit var adoptViewModel: AdoptViewModel
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.a_home_fragment, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adoptViewModel = ViewModelProvider(requireParentFragment(), viewModelFactory).get(AdoptViewModel::class.java)
        adoptViewModel.text.observe(viewLifecycleOwner, Observer { })
    }
}
