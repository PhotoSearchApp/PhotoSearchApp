package com.hanmo.simplephotosearchapp.di.module

import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import com.hanmo.simplephotosearchapp.ui.search.PhotoSearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [KeywordModule::class, ContentModule::class])
    abstract fun photoSearchActivity() : PhotoSearchActivity

}