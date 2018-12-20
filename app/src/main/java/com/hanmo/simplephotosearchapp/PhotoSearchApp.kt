package com.hanmo.simplephotosearchapp

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PhotoSearchApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val applicationComponent =
                DaggerAppComponent
                        .builder()
                        .application(this)
                        .build()

        applicationComponent.inject(this)

        return applicationComponent
    }

}