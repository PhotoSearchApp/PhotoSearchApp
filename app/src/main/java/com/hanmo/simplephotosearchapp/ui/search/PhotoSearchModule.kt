package com.hanmo.simplephotosearchapp.ui.search

import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import dagger.Binds
import dagger.Module

@Module
abstract class PhotoSearchModule {

    @ActivityScoped
    @Binds
    abstract fun photoSearchPresenter(photoSearchPresenter: PhotoSearchPresenter) : PhotoSearchContract.Presenter
}