package com.hanmo.simplephotosearchapp.ui.search

import android.os.Bundle
import com.hanmo.simplephotosearchapp.R
import com.hanmo.simplephotosearchapp.RxEventBus
import com.hanmo.simplephotosearchapp.base.BaseActivity
import com.hanmo.simplephotosearchapp.data.realm.RealmService
import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import com.hanmo.simplephotosearchapp.model.Keyword
import com.hanmo.simplephotosearchapp.ui.search.content.ContentFragment
import com.hanmo.simplephotosearchapp.ui.search.keyword.KeywordFragment
import dagger.Lazy
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_photo_search.*
import javax.inject.Inject

@ActivityScoped
class PhotoSearchActivity : BaseActivity() {

    @Inject
    lateinit var keywordFragmentProvider: Lazy<KeywordFragment>

    @Inject
    lateinit var contentFragmentProvider: Lazy<ContentFragment>

    private val compositeDisposable : CompositeDisposable by lazy { CompositeDisposable() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_search)

        initKeywordContainer()
        initContentContainer()

        initToolbarKeyword()
    }

    private fun initToolbarKeyword() {
        RealmService.getFirstKeyword()?.let { firstKeyword -> toolbarKeyword.text = firstKeyword }
        RxEventBus.filteredObservable(Keyword::class.java)
                .subscribe { keyword ->
                    toolbarKeyword.text = keyword.keywordName
                }.apply { compositeDisposable.add(this) }
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

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}