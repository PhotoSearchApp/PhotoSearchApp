package com.hanmo.simplephotosearchapp.ui.search.content

import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import com.hanmo.simplephotosearchapp.repository.PhotoSearchRepository
import javax.inject.Inject

@ActivityScoped
class ContentPresenter @Inject constructor(private val photoSearchRepository: PhotoSearchRepository) : ContentContract.Presenter {

    private var contentView : ContentContract.View? = null

    override fun takeView(view: ContentContract.View) {
        contentView = view
    }

    override fun dropView() {
        contentView = null
    }

}