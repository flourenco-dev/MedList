package com.fabiolourenco.medlist

import com.fabiolourenco.medlist.utils.injection.DaggerInjectionComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import javax.inject.Singleton

@Singleton
class MedApp : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerInjectionComponent.builder().application(this).build()
    }
}