package com.hanmo.simplephotosearchapp.ui.search.keyword

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hanmo.simplephotosearchapp.KEYWORD
import com.hanmo.simplephotosearchapp.R
import com.hanmo.simplephotosearchapp.base.BaseFragment
import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import com.hanmo.simplephotosearchapp.ui.search.PhotoSearchAdapter
import kotlinx.android.synthetic.main.fragment_keyword.*
import javax.inject.Inject

@ActivityScoped
class KeywordFragment @Inject constructor() : BaseFragment(), KeywordContract.View {

    @Inject
    lateinit var presenter: KeywordPresenter

    private val keywordAdapter : PhotoSearchAdapter by lazy { PhotoSearchAdapter(KEYWORD) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_keyword, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        presenter.takeView(this)
    }

    override fun initKeywordList() {
        keywordList?.run {
            adapter = keywordAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    override fun loadKeywordList() {
        keywordAdapter.loadKeyword()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

}