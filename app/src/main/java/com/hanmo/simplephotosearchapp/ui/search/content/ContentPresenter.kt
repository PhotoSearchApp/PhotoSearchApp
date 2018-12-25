package com.hanmo.simplephotosearchapp.ui.search.content

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.hanmo.simplephotosearchapp.R
import com.hanmo.simplephotosearchapp.util.RxEventBus
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

    private fun isNetworkAvailable() : Boolean {
        val connectivityManager = contentView?.getContext()?.getSystemService(Context.CONNECTIVITY_SERVICE)
        return if (connectivityManager is ConnectivityManager) {
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            networkInfo?.isConnected ?: false
        } else false
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
            if (isNetworkAvailable()) {
                photoSearchRepository.getPhotos(keyword, 10, page, getString(R.string.method), getString(R.string.authKey), getString(R.string.format), 1)
                        .doOnError { contentView?.hideProgress() }
                        .doOnSuccess { contentView?.hideProgress() }
                        .subscribe(
                                { res ->
                                    if (res.isSuccessful) {
                                        //contentList가 null이 아니라면 View에 data 전달
                                        res.body()?.photos?.photo?.let { contentList ->
                                            //page가 1이면 처음 로드하는 로직을 타야한다.
                                            if (page == 1) {
                                                if (contentList.isNotEmpty()) { //contentList size가 0이 아닐경우 View에 data 전달
                                                    contentView?.showContentList(contentList)
                                                } else { //contentList size가 0이면 결과 없음을 출력
                                                    contentView?.showNotResult()
                                                }
                                            } else {
                                                contentView?.updateContentList(contentList)
                                            }
                                        } ?: kotlin.run { //contentList가 null이면 결과 없음을 출력
                                            contentView?.showNotResult()
                                        }
                                    }
                                }, { error -> contentView?.showError("네트워크 오류가 발생하였습니다.") }
                        ).apply { compositeDisposable.add(this) }
            } else {
                contentView?.showError("네트워크 연결상태를 확인해주십시오.")
            }
        }
    }

    override fun dropView() {
        contentView = null
    }

}