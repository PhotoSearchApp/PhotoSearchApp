package com.hanmo.simplephotosearchapp.ui.keyword

import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class KeywordPresenter @Inject constructor() : KeywordContract.Presenter {

    private var keywordView : KeywordContract.View? = null

    override fun takeView(view: KeywordContract.View) {
        keywordView = view
    }

    override fun dropView() {
        keywordView = null
    }

}