package com.hanmo.simplephotosearchapp.network

import com.hanmo.simplephotosearchapp.model.Photos
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface PhotoSearchApi {

    @GET("/rest/")
    fun getPhotos(@Query("method") method: String, @Query("api_key") apiKey: String,
                  @Query("text") keyword: String, @Query("per_page") perPage: String,
                  @Query("page") page: String, @Query("format") format: String, @Query("nojsoncallback") callback : Int) : Single<Response<Photos>>
}