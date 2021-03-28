package com.karuna.pages

import android.app.Application
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeLoggers()
    }

    /**
     * Initialize logging libraries
     */
    private fun initializeLoggers() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}