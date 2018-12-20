package com.hanmo.simplephotosearchapp.base

interface BasePresenter<T> {
    
    fun takeView(view: T)

    fun dropView()

}
