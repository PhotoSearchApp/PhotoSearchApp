package com.hanmo.simplephotosearchapp.ui.search.keyword

import com.hanmo.simplephotosearchapp.base.BasePresenter
import com.hanmo.simplephotosearchapp.base.BaseView

interface KeywordContract {

    interface View : BaseView {
        fun initKeywordList()
        fun loadKeywordList()
    }

    interface Presenter : BasePresenter<View> {
        fun clickedKeyword(keywordName: String)
    }

}