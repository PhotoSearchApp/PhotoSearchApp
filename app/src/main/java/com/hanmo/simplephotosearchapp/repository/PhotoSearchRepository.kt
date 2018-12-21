package com.hanmo.simplephotosearchapp.repository

import com.hanmo.simplephotosearchapp.model.Photos
import com.hanmo.simplephotosearchapp.network.PhotoSearchApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class PhotoSearchRepository(private val photoSearchApi: PhotoSearchApi) {

    fun getPhotos(keyword: String, perPage: Int, page: Int) : Single<Response<Photos>> {
        val authKey = "2f904f1669187c7860cae324d891ccd3"
        val method = "flickr.photos.search"
        val format = "json"
        val callback = 1
        return photoSearchApi.getPhotos(method, authKey, keyword, perPage, page, format, callback)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}