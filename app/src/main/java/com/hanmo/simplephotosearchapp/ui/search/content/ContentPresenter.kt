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
    private lateinit var keyword : String

    override fun takeView(view: ContentContract.View) {
        contentView = view
        contentView?.initContentList()

        //첫 시작시 가장 첫번째 키워드로 리스트를 보여주기 위하여
        RealmService.getFirstKeyword()?.let { firstKeyword ->
            keyword = firstKeyword
            loadPhotoList(1)
        }
    }

    override fun loadPhotoList(page: Int) {
        contentView?.getContext()?.run {
            photoSearchRepository.getPhotos(keyword, 10, page, getString(R.string.method), getString(R.string.authKey), getString(R.string.format), 1)
                    .subscribe(
                            { res ->
                                res.body()?.photos?.photo?.let {
                                    if (page == 1) {
                                        contentView?.showContentList(it)
                                    } else {
                                        contentView?.updateContentList(it)
                                    }
                                }
                            }, { error -> Log.e("photosearch ", "errror is : $error") }
                    )
        }
    }

    override fun dropView() {
        contentView = null
    }

}