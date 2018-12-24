package com.hanmo.simplephotosearchapp.ui.search.content

import android.util.Log
import com.hanmo.simplephotosearchapp.R
import com.hanmo.simplephotosearchapp.RxEventBus
import com.hanmo.simplephotosearchapp.data.realm.RealmService
import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import com.hanmo.simplephotosearchapp.model.Keyword
import com.hanmo.simplephotosearchapp.repository.PhotoSearchRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@ActivityScoped
class ContentPresenter @Inject constructor(private val photoSearchRepository: PhotoSearchRepository) : ContentContract.Presenter {

    private var contentView : ContentContract.View? = null
    private lateinit var keyword : String
    private val compositeDisposable : CompositeDisposable by lazy { CompositeDisposable() }

    override fun takeView(view: ContentContract.View) {
        contentView = view
        contentView?.initContentList()

        //첫 시작시 가장 첫번째 키워드로 리스트를 보여주기 위하여
        RealmService.getFirstKeyword()?.let { firstKeyword ->
            keyword = firstKeyword
            loadPhotoList(1)
        }

        keywordClickedObservable()
    }

    override fun keywordClickedObservable() {
        RxEventBus.filteredObservable(Keyword::class.java)
                .subscribe { keyword ->
                    this.keyword = keyword.keywordName
                    loadPhotoList(1)
                }.apply { compositeDisposable.add(this) }
    }

    override fun loadPhotoList(page: Int) {
        contentView?.getContext()?.run {

            contentView?.showProgress()
            photoSearchRepository.getPhotos(keyword, 10, page, getString(R.string.method), getString(R.string.authKey), getString(R.string.format), 1)
                    .doOnError { contentView?.hideProgress() }
                    .doOnSuccess { contentView?.hideProgress() }
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
                    ).apply { compositeDisposable.add(this) }
        }
    }

    override fun dropView() {
        contentView = null
    }

}