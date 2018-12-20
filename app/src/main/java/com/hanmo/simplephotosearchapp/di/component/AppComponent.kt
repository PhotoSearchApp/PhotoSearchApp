package com.hanmo.simplephotosearchapp.di.component

import android.content.Context
import com.hanmo.simplephotosearchapp.PhotoSearchApp
import com.hanmo.simplephotosearchapp.di.module.ActivityBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBindingModule::class])
interface AppComponent : AndroidInjector<PhotoSearchApp> {

    override fun inject(instance: PhotoSearchApp?)

    @Component.Builder
    interface Builder {
        fun build() : AppComponent

        @BindsInstance
        fun application(applicationContext: Context) : Builder
    }

}