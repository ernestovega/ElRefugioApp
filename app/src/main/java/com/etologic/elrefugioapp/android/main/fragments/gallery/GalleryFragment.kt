package com.etologic.elrefugioapp.android.main.fragments.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.etologic.elrefugioapp.R.layout
import com.etologic.elrefugioapp.android.main.activity.BaseMainFragment
import kotlinx.android.synthetic.main.gallery_fragment.*
import javax.inject.Inject

class GalleryFragment : BaseMainFragment() {
    
    @Inject
    lateinit var galleryViewModelFactory: GalleryViewModelFactory
    private lateinit var galleryViewModel: GalleryViewModel
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout.gallery_fragment, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        galleryViewModel = ViewModelProvider(this, galleryViewModelFactory).get(GalleryViewModel::class.java)
        galleryViewModel.text.observe(viewLifecycleOwner, Observer { text_gallery?.text = it })
    }
}
