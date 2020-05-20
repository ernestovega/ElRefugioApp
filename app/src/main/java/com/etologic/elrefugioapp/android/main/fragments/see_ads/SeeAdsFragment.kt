package com.etologic.elrefugioapp.android.main.fragments.see_ads

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import com.etologic.elrefugioapp.R
import com.etologic.elrefugioapp.android.global.extensions.setOnSecureClickListener
import com.etologic.elrefugioapp.android.main.activity.MainBaseFragment
import com.etologic.elrefugioapp.utils.SharedPreferencesUtils
import kotlinx.android.synthetic.main.c_see_ads_fragment.*
import java.util.*

class SeeAdsFragment : MainBaseFragment() {
    
    companion object {
        private const val SP_KEY_AD_SEEN_TIMES: String = "sp_key_ad_seen_times"
        private const val COUNTDOWN_DURATION: Long = 10000
        private const val A_SECOND_IN_MILLIS: Long = 1000
    }
    
    private lateinit var pbLoading: ProgressBar
    private lateinit var rlActionsContainer: RelativeLayout
    private lateinit var tvCountdown: TextView
    private lateinit var btSeeOther: Button
    private lateinit var tvYouHaveSeenN: TextView
    private lateinit var tvThanks: TextView
    private var countdown: CountDownTimer
    
    init {
        countdown = object : CountDownTimer(COUNTDOWN_DURATION, A_SECOND_IN_MILLIS) {
            override fun onTick(millisUntilFinished: Long) {
                tvCountdown.text = "${millisUntilFinished / A_SECOND_IN_MILLIS}"
            }
            
            override fun onFinish() {
                startAd()
            }
        }
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.c_see_ads_fragment, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initObservers()
        startAd()
    }
    
    private fun initViews(view: View) {
        pbLoading = view.findViewById(R.id.pbSeeAdsLoading)
        rlActionsContainer = view.findViewById(R.id.rlSeeAdsActionsContainer)
        tvCountdown = view.findViewById(R.id.tvSeeAdsSeeAnotherInSecondsNumber)
        btSeeOther = view.findViewById(R.id.btSeeAdsSeeOther)
        tvYouHaveSeenN = view.findViewById(R.id.tvSeeAdsYouHaveSeenN)
        tvThanks = view.findViewById(R.id.tvSeeAdsThanks)
        
        btSeeOther.setOnSecureClickListener {
            countdown.cancel()
            startAd()
        }
    }
    
    private fun initObservers() {
        activityViewModel?.getInterstitialAdLoaded()?.observe(viewLifecycleOwner, Observer {
            pbLoading.visibility = GONE
        })
        activityViewModel?.getInterstitialAdFailedToLoad()?.observe(viewLifecycleOwner, Observer {
            endAd()
        })
        activityViewModel?.getInterstitialAdFinished()?.observe(viewLifecycleOwner, Observer {
            incrementTimesSeen()
            endAd()
        })
    }
    
    private fun startAd() {
        pbLoading.visibility = VISIBLE
        rlActionsContainer.visibility = GONE
        tvSeeAdsThanks.visibility = VISIBLE
        activityViewModel?.loadInterstitial()
    }
    
    private fun endAd() {
        pbLoading.visibility = GONE
        tvYouHaveSeenN.text = String.format(
            Locale.getDefault(),
            getString(R.string.has_visto_n),
            getTimesSeen()
        )
        tvSeeAdsThanks.visibility = GONE
        rlActionsContainer.visibility = VISIBLE
        countdown.start()
    }
    
    private fun getTimesSeen() = SharedPreferencesUtils.getData(requireContext(), SP_KEY_AD_SEEN_TIMES, Int::class.java) ?: 0
    
    private fun incrementTimesSeen() {
        SharedPreferencesUtils.storeData(
            requireContext(),
            SP_KEY_AD_SEEN_TIMES,
            (SharedPreferencesUtils.getData(requireContext(), SP_KEY_AD_SEEN_TIMES, Int::class.java) ?: 0) + 1
        )
    }
}
