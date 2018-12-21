package com.hanmo.simplephotosearchapp.model

import com.google.gson.annotations.SerializedName


data class Photos(@SerializedName("photos") var photos : PhotoList)

data class PhotoList(@SerializedName("photo") var photo : MutableList<Photo>?)

data class Photo(
        @SerializedName("id")
        var id: String?,
        @SerializedName("secret")
        var secret: String?,
        @SerializedName("server")
        var server: String?,
        @SerializedName("farm")
        var farm: Int?,
        @SerializedName("title")
        var title: String?
)

