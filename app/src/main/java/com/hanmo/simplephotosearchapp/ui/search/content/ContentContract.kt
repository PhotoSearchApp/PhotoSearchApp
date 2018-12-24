package com.hanmo.simplephotosearchapp.ui.search.content

import android.content.Context
import com.hanmo.simplephotosearchapp.base.BasePresenter
import com.hanmo.simplephotosearchapp.base.BaseView
import com.hanmo.simplephotosearchapp.model.Photo

interface ContentContract {

    interface View : BaseView {
        fun getContext() : Context?
        fun initContentList()
        fun showContentList(contentList : MutableList<Photo>)
        fun updateContentList(contentList : MutableList<Photo>)

        fun showProgress()
        fun hideProgress()
    }

    interface Presenter : BasePresenter<View> {
        fun loadPhotoList(page : Int)
        fun keywordClickedObservable()
    }

}