package com.hanmo.simplephotosearchapp.ui.search

import android.os.Bundle
import com.hanmo.simplephotosearchapp.R
import com.hanmo.simplephotosearchapp.base.BaseActivity
import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import com.hanmo.simplephotosearchapp.ui.content.ContentFragment
import com.hanmo.simplephotosearchapp.ui.keyword.KeywordFragment
import dagger.Lazy
import javax.inject.Inject

@ActivityScoped
class PhotoSearchActivity : BaseActivity() {

    @Inject
    lateinit var keywordFragmentProvider: Lazy<KeywordFragment>

    @Inject
    lateinit var contentFragmentProvider: Lazy<ContentFragment>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_search)

        initKeywordContainer()
        initContentContainer()
    }

    private fun initKeywordContainer() {

        var keywordFragment: KeywordFragment? = supportFragmentManager.findFragmentById(R.id.keywordContainer) as KeywordFragment?

        if (keywordFragment == null) {

            val args = Bundle()
            keywordFragment = keywordFragmentProvider.get()
            keywordFragment?.arguments = args

            replaceFragment(R.id.keywordContainer, keywordFragment)
        }
    }

    private fun initContentContainer() {

        var contentFragment: ContentFragment? = supportFragmentManager.findFragmentById(R.id.contentContainer) as ContentFragment?

        if (contentFragment == null) {

            val args = Bundle()
            contentFragment = contentFragmentProvider.get()
            contentFragment?.arguments = args

            replaceFragment(R.id.contentContainer, contentFragment)
        }
    }

}