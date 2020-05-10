package com.etologic.elrefugioapp.android.main.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.etologic.elrefugioapp.R
import com.etologic.elrefugioapp.R.id
import com.etologic.elrefugioapp.R.layout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private lateinit var mainViewModel: MainViewModel
    private lateinit var appBarConfiguration: AppBarConfiguration
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.main_activity)
        if (!initToolbarAndDrawerAndNavigation()) return
        setListeners()
        initViewModel()
    }
    
    private fun initToolbarAndDrawerAndNavigation(): Boolean {
        setSupportActionBar(findViewById(id.mainToolbar))
        val navController: NavController = findNavController(id.fMainNavHost)
        val navigationView: NavigationView = findViewById(id.nvMain) ?: return false
        val drawerLayout: DrawerLayout = findViewById<DrawerLayout>(id.dlMain) ?: return false
        appBarConfiguration = AppBarConfiguration(setOf(id.nav_home, id.nav_gallery, id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navigationView.setupWithNavController(navController)
        return true
    }
    
    private fun setListeners() {
        findViewById<FloatingActionButton>(id.mainFab)?.setOnClickListener { view ->
            Snackbar
                .make(view, "Replace with your own action", LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }
    }
    
    private fun initViewModel() {
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
//        mainViewModel.text.observe(this, Observer {  })
    }
    
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.main_activity2, menu)
//        return true
//    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        item.onNavDestinationSelected(findNavController(id.fMainNavHost)) || super.onOptionsItemSelected(item)
    
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fMainNavHost)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
