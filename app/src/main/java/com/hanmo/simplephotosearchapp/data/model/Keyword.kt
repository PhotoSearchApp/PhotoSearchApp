package com.hanmo.simplephotosearchapp.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Keyword : RealmObject() {

    @PrimaryKey
    var id : Int = 0

    var name : String? = null
}

