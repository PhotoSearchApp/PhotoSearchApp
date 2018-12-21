package com.hanmo.simplephotosearchapp.model

import com.google.gson.annotations.SerializedName

data class Photos(@SerializedName("photos") var photo : MutableList<Photo>?, var stat: String?)

data class Photo(
        var id: String?,
        var secret: String?,
        var server: String?,
        var farm: Int?,
        var title: String?
)