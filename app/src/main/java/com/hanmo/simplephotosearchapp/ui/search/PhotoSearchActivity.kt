package com.hanmo.simplephotosearchapp.ui.search

import android.os.Bundle
import com.hanmo.simplephotosearchapp.R
import com.hanmo.simplephotosearchapp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_photo_search.*

class PhotoSearchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_search)
        setSupportActionBar(searchToolbar)

    }
}