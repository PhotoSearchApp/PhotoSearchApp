package com.hanmo.simplephotosearchapp.ui.content

import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ContentPresenter @Inject constructor() : ContentContract.Presenter {

    private var contentView : ContentContract.View? = null

    override fun takeView(view: ContentContract.View) {
        contentView = view
    }

    override fun dropView() {
        contentView = null
    }

}