package com.etologic.elrefugioapp.android.main.fragments.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.etologic.elrefugioapp.R.layout
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.slideshow_fragment.*
import javax.inject.Inject

class SlideshowFragment : DaggerFragment() {
    
    @Inject
    lateinit var slideshowViewModelFactory: SlideshowViewModelFactory
    private lateinit var slideshowViewModel: SlideshowViewModel
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout.slideshow_fragment, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        slideshowViewModel = ViewModelProvider(this, slideshowViewModelFactory).get(SlideshowViewModel::class.java)
        slideshowViewModel.text.observe(viewLifecycleOwner, Observer { text_slideshow?.text = it })
    }
}
