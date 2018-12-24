package com.hanmo.simplephotosearchapp.ui.search.keyword

import com.hanmo.simplephotosearchapp.RxEventBus.post
import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import com.hanmo.simplephotosearchapp.model.Keyword
import javax.inject.Inject

@ActivityScoped
class KeywordPresenter @Inject constructor() : KeywordContract.Presenter {

    private var keywordView : KeywordContract.View? = null

    override fun takeView(view: KeywordContract.View) {
        keywordView = view

        keywordView?.run {
            initKeywordList()
            loadKeywordList()
        }
    }

    override fun clickedKeyword(keywordName: String) {
        post(Keyword(keywordName))
    }

    override fun dropView() {
        keywordView = null
    }

}