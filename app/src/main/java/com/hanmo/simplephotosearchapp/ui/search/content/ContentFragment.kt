package com.hanmo.simplephotosearchapp.ui.search.content

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hanmo.simplephotosearchapp.CONTENT
import com.hanmo.simplephotosearchapp.R
import com.hanmo.simplephotosearchapp.base.BaseFragment
import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import com.hanmo.simplephotosearchapp.model.Photo
import com.hanmo.simplephotosearchapp.ui.search.PhotoSearchAdapter
import kotlinx.android.synthetic.main.fragment_content.*
import javax.inject.Inject

@ActivityScoped
class ContentFragment @Inject constructor() : BaseFragment(), ContentContract.View {

    @Inject
    lateinit var presenter: ContentPresenter

    private val contentAdapter : PhotoSearchAdapter by lazy { PhotoSearchAdapter(CONTENT) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.takeView(this)
    }

    override fun initContentList() {
        contentList?.run {
            adapter = contentAdapter

            val infiniteScrollListener = object : InfiniteScrollListener(layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    presenter.loadPhotoList(page)
                }
            }

            addOnScrollListener(infiniteScrollListener)
        }
    }

    override fun showContentList(contentList: MutableList<Photo>) {
        contentAdapter.loadContent(contentList)
    }

    override fun updateContentList(contentList: MutableList<Photo>) {
        contentAdapter.updateContent(contentList)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun getContext(): Context? {
        return activity
    }

}