package com.etologic.elrefugioapp.android.main.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.etologic.elrefugioapp.R
import com.etologic.elrefugioapp.R.id
import com.etologic.elrefugioapp.R.layout
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
    private lateinit var mainViewModel: MainViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var drawerLayout: DrawerLayout
    private var lastBackPress: Long = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.main_activity)
        if (!initToolbarAndDrawerAndNavigation()) return
        initViewModel()
    }
    
    private fun initToolbarAndDrawerAndNavigation(): Boolean {
        val toolbar = findViewById<Toolbar>(id.tMain)
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(id.ctlMain)
        setSupportActionBar(toolbar)
        val navController: NavController = findNavController(id.fMainNavHost)
        val navigationView: NavigationView = findViewById(id.nvMain) ?: return false
        drawerLayout = findViewById(id.dlMain) ?: return false
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
        return true
    }
    
    override fun onSupportNavigateUp(): Boolean = findNavController(id.fMainNavHost).navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu_actionbar, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        item.onNavDestinationSelected(findNavController(id.fMainNavHost)) || super.onOptionsItemSelected(item)
    
    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
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
