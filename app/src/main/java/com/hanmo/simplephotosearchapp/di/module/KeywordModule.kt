package com.hanmo.simplephotosearchapp.di.module

import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import com.hanmo.simplephotosearchapp.di.annotation.FragmentScoped
import com.hanmo.simplephotosearchapp.ui.search.keyword.KeywordContract
import com.hanmo.simplephotosearchapp.ui.search.keyword.KeywordFragment
import com.hanmo.simplephotosearchapp.ui.search.keyword.KeywordPresenter
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class KeywordModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun keywordFragment(): KeywordFragment

    @ActivityScoped
    @Binds
    abstract fun keywordPresenter(keywordPresenter: KeywordPresenter) : KeywordContract.Presenter
}