package com.etologic.elrefugioapp.android.main.activity

import android.os.Bundle
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
import com.etologic.elrefugioapp.BuildConfig
import com.etologic.elrefugioapp.R
import com.etologic.elrefugioapp.R.id
import com.etologic.elrefugioapp.R.layout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    
    companion object {
        internal const val LAST_BACKPRESSED_MIN_TIME: Long = 2000
    }
    
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var mInterstitialAd: InterstitialAd
    private var lastBackPress: Long = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.main_activity)
    
        initAds()
        initToolbarAndDrawerAndNavigation()
        initViewModel()
    }
    
    private fun initAds() {
        MobileAds.initialize(this) {}
        mInterstitialAd = InterstitialAd(this)
        //FixMe: The ELSE one is a the test id too
        mInterstitialAd.adUnitId = if (BuildConfig.DEBUG) "ca-app-pub-3940256099942544/1033173712" else "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                viewModel.setInterstitialAdLoaded()
                mInterstitialAd.show()
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
    
    private fun initToolbarAndDrawerAndNavigation() {
        val toolbar = findViewById<Toolbar>(id.tMain)
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(id.ctlMain)
        setSupportActionBar(toolbar)
        actionBar?.title = "JAJAJAJ"
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
        collapsingToolbarLayout.setupWithNavController(toolbar, navController, appBarConfiguration)
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
        if (!mInterstitialAd.isLoaded)
            mInterstitialAd.loadAd(AdRequest.Builder().build())
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
