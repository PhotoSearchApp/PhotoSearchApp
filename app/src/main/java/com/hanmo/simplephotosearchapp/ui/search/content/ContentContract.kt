package com.hanmo.simplephotosearchapp.ui.search.content

import android.content.Context
import com.hanmo.simplephotosearchapp.base.BasePresenter
import com.hanmo.simplephotosearchapp.base.BaseView

interface ContentContract {

    interface View : BaseView {
        fun getContext() : Context?
    }

    interface Presenter : BasePresenter<View> {
        fun loadPhotoList(keyword : String, page : Int)
    }

}