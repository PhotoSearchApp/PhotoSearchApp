package com.hanmo.simplephotosearchapp.ui.search.content

import android.util.Log
import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import com.hanmo.simplephotosearchapp.repository.PhotoSearchRepository
import javax.inject.Inject

@ActivityScoped
class ContentPresenter @Inject constructor(private val photoSearchRepository: PhotoSearchRepository) : ContentContract.Presenter {

    private var contentView : ContentContract.View? = null

    override fun takeView(view: ContentContract.View) {
        contentView = view

        photoSearchRepository.getPhotos("Apple", 10, 1)
                .subscribe(
                        { res -> Log.e("photosearch ", res.body()?.photos.toString())
                        }, { error -> Log.e("photosearch ", "errror is : $error") }
                )
    }

    override fun dropView() {
        contentView = null
    }

}