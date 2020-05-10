package com.etologic.elrefugioapp.global

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class ElRefugioApp : DaggerApplication() {
    
    override fun applicationInjector(): AndroidInjector<out ElRefugioApp> {
        return DaggerAppComponent.factory().create(this)
    }
}
