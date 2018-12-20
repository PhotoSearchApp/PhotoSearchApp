package com.hanmo.simplephotosearchapp.ui.content

import com.hanmo.simplephotosearchapp.base.BasePresenter
import com.hanmo.simplephotosearchapp.base.BaseView

interface ContentContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {

    }

}