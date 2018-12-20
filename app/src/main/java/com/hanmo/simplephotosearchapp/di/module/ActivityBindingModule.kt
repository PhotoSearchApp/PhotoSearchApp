package com.hanmo.simplephotosearchapp.di.module

import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import com.hanmo.simplephotosearchapp.ui.search.PhotoSearchActivity
import com.hanmo.simplephotosearchapp.ui.search.PhotoSearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [PhotoSearchModule::class])
    abstract fun photoSearchActivity() : PhotoSearchActivity

}