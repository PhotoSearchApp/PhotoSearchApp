package com.hanmo.simplephotosearchapp.ui.search

import com.hanmo.simplephotosearchapp.base.BasePresenter
import com.hanmo.simplephotosearchapp.base.BaseView

interface PhotoSearchContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {

    }

}