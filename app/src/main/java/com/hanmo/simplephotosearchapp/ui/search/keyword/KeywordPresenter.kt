package com.hanmo.simplephotosearchapp.ui.search.keyword

import android.util.Log
import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@ActivityScoped
class KeywordPresenter @Inject constructor() : KeywordContract.Presenter {

    private var keywordView : KeywordContract.View? = null
    private val itemClickSubject = PublishSubject.create<Int>()

    override fun takeView(view: KeywordContract.View) {
        keywordView = view

        keywordView?.run {
            initKeywordList()
            loadKeywordList()
        }
    }

    override fun clickedKeyword(keywordId: Int) {
        itemClickSubject.onNext(keywordId)
    }

    override fun dropView() {
        keywordView = null
    }

}