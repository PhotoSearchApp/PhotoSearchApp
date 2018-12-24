package com.hanmo.simplephotosearchapp.repository

import com.hanmo.simplephotosearchapp.model.Photos
import com.hanmo.simplephotosearchapp.network.PhotoSearchApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class PhotoSearchRepository(private val photoSearchApi: PhotoSearchApi) {

    fun getPhotos(keyword: String, perPage: Int, page: Int, method : String, authKey : String, format : String, callback : Int) : Single<Response<Photos>> {
        return photoSearchApi.getPhotos(method, authKey, keyword, perPage, page, format, callback)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}