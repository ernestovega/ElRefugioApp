package com.etologic.elrefugioapp.android.main.fragments.see_ads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import com.etologic.elrefugioapp.R
import com.etologic.elrefugioapp.android.main.activity.MainBaseFragment

class SeeAdsFragment : MainBaseFragment() {
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.c_see_ads_fragment, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModel?.loadIntersticial()
        activityViewModel?.getInterstitialFinished()?.observe(viewLifecycleOwner, Observer {
            view.findViewById<RelativeLayout>(R.id.rlSeeAdsThanksContainer).visibility = VISIBLE
        })
    }
}
