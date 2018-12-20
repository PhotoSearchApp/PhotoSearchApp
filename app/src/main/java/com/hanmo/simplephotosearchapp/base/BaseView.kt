package com.hanmo.simplephotosearchapp.base

interface BaseView {

    fun showLoading()
    fun hideLoading()
    fun showError(error : String)
    
}