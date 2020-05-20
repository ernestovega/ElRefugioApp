package com.etologic.elrefugioapp.android.main.activity

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.etologic.elrefugioapp.R
import com.etologic.elrefugioapp.R.id
import com.etologic.elrefugioapp.R.layout
import com.google.ads.consent.ConsentInfoUpdateListener
import com.google.ads.consent.ConsentInformation
import com.google.ads.consent.ConsentStatus
import com.google.ads.consent.ConsentStatus.*
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.*
import com.google.android.gms.ads.AdRequest.Builder
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity_actionbar.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    
    companion object {
        internal const val LAST_BACKPRESSED_MIN_TIME: Long = 2000
    }
    
    private var areConsentsAccepted: Boolean = false
    
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var interstitialAd: InterstitialAd
    private lateinit var adView: AdView
    private var lastBackPress: Long = 0
    private val bannerAdSize: AdSize
        get() {
            val display = windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)
            val density = outMetrics.density
            var adWidthPixels = llMainBannerContainer?.width?.toFloat() ?: 0f
            if (adWidthPixels == 0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }
            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
        }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.main_activity)
    
        initToolbarAndDrawerAndNavigation()
        initViewModel()
    
        initAds()
    }
    
    private fun initAds() {
        MobileAds.initialize(this) {}
        requestConsents()
        initInterstitial()
        initBanner()
    }
    
    private fun initInterstitial() {
        interstitialAd = InterstitialAd(this)
        interstitialAd.adUnitId = viewModel.getInterstitialAdUnit()
        interstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                viewModel.setInterstitialAdLoaded()
                interstitialAd.show()
            }
            
            override fun onAdFailedToLoad(errorCode: Int) {
                viewModel.setInterstitialAdFailedToLoad()
            }
            
            override fun onAdOpened() {}
            
            override fun onAdClicked() {}
            
            override fun onAdLeftApplication() {}
            
            override fun onAdClosed() {
                viewModel.setInterstitialAdClosed()
            }
        }
    }
    
    private fun initBanner() {
        adView = AdView(this)
        adView.adUnitId = viewModel.getBannerAdUnit()
        adView.adSize = bannerAdSize
        llMainBannerContainer.addView(adView)
        val adRequest = Builder()
            .addTestDevice("AAA1B95B86CE0F6FF6D741779CB93FFB")
        
        if (areConsentsAccepted) {
            val extras = Bundle()
            extras.putString("npa", "1")
            adRequest.addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
        }
        
        adView.loadAd(adRequest.build())
    }
    
    private fun requestConsents() {
        val consentInformation = ConsentInformation.getInstance(this)
        val publisherIds = arrayOf("ca-app-pub-2237700199215764~4393457531")
        consentInformation.requestConsentInfoUpdate(publisherIds, object : ConsentInfoUpdateListener {
            override fun onConsentInfoUpdated(consentStatus: ConsentStatus) {
                when (consentStatus) {
                    UNKNOWN -> areConsentsAccepted = false
                    NON_PERSONALIZED -> areConsentsAccepted = false
                    PERSONALIZED -> areConsentsAccepted = true
                }
            }
            
            override fun onFailedToUpdateConsentInfo(errorDescription: String) {
                // User's consent status failed to update.
            }
        })
    }
    
    private fun initToolbarAndDrawerAndNavigation() {
        val toolbar = findViewById<Toolbar>(id.tMain)
        setSupportActionBar(toolbar)
        val navController: NavController = findNavController(id.fMainNavHost)
        val navigationView: NavigationView = findViewById(id.nvMain)
        drawerLayout = findViewById(id.dlMain)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                id.nav_home,
                id.nav_adopt,
                id.nav_welcoming,
                id.nav_see_ads,
                id.nav_guau_walker,
                id.nav_vet_services
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        toolbar.setupWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)
    }
    
    override fun onSupportNavigateUp(): Boolean = findNavController(id.fMainNavHost).navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu_actionbar, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        item.onNavDestinationSelected(findNavController(id.fMainNavHost)) || super.onOptionsItemSelected(item)
    
    private fun initViewModel() {
        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        viewModel.getLoadAd().observe(this, Observer { this.showAd() })
    }
    
    private fun showAd() {
        if (!interstitialAd.isLoaded)
            interstitialAd.loadAd(Builder().build())
    }
    
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START, true)
        else {
            val currentTimeMillis = System.currentTimeMillis()
            if (currentTimeMillis - lastBackPress > LAST_BACKPRESSED_MIN_TIME) {
                Snackbar.make(drawerLayout, R.string.press_again_to_exit, Snackbar.LENGTH_LONG).show()
                lastBackPress = currentTimeMillis
            } else
                finish()
        }
    }
}
