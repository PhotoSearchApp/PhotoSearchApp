package com.hanmo.simplephotosearchapp.di.module

import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import com.hanmo.simplephotosearchapp.di.annotation.FragmentScoped
import com.hanmo.simplephotosearchapp.ui.search.content.ContentApiModule
import com.hanmo.simplephotosearchapp.ui.search.content.ContentContract
import com.hanmo.simplephotosearchapp.ui.search.content.ContentFragment
import com.hanmo.simplephotosearchapp.ui.search.content.ContentPresenter
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ContentApiModule::class])
abstract class ContentModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun contentFragment(): ContentFragment

    @ActivityScoped
    @Binds
    abstract fun contentPresenter(contentPresenter: ContentPresenter) : ContentContract.Presenter
}