package com.hanmo.simplephotosearchapp.ui.search.content

import android.util.Log
import com.hanmo.simplephotosearchapp.R
import com.hanmo.simplephotosearchapp.data.realm.RealmService
import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import com.hanmo.simplephotosearchapp.repository.PhotoSearchRepository
import javax.inject.Inject

@ActivityScoped
class ContentPresenter @Inject constructor(private val photoSearchRepository: PhotoSearchRepository) : ContentContract.Presenter {

    private var contentView : ContentContract.View? = null

    override fun takeView(view: ContentContract.View) {
        contentView = view

        //첫 시작시 가장 첫번째 키워드로 리스트를 보여주기 위하여
        RealmService.getFirstKeyword()?.let { firstKeyword -> loadPhotoList(firstKeyword, 1) }
    }

    override fun loadPhotoList(keyword: String, page: Int) {
        contentView?.getContext()?.run {
            photoSearchRepository.getPhotos("Apple", 10, page, getString(R.string.method), getString(R.string.authKey), getString(R.string.format), 1)
                    .subscribe(
                            { res -> Log.e("photosearch ", res.body()?.photos.toString())
                            }, { error -> Log.e("photosearch ", "errror is : $error") }
                    )
        }
    }

    override fun dropView() {
        contentView = null
    }

}