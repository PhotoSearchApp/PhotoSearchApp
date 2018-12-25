package com.hanmo.simplephotosearchapp.ui.search.content

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.hanmo.simplephotosearchapp.CONTENT
import com.hanmo.simplephotosearchapp.R
import com.hanmo.simplephotosearchapp.base.BaseFragment
import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import com.hanmo.simplephotosearchapp.model.Photo
import com.hanmo.simplephotosearchapp.ui.search.PhotoSearchAdapter
import kotlinx.android.synthetic.main.fragment_content.*
import org.jetbrains.anko.toast
import javax.inject.Inject

@ActivityScoped
class ContentFragment @Inject constructor() : BaseFragment(), ContentContract.View {

    @Inject
    lateinit var presenter: ContentPresenter

    private val contentAdapter : PhotoSearchAdapter by lazy { PhotoSearchAdapter(CONTENT) }

    private val dropDownAnimation : LayoutAnimationController by lazy { AnimationUtils.loadLayoutAnimation(context, R.anim.layout_anim_drop_down) }

    private lateinit var infiniteScrollListener : InfiniteScrollListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.takeView(this)
    }

    override fun initContentList() {
        contentListView?.run {
            adapter = contentAdapter

            infiniteScrollListener = object : InfiniteScrollListener(layoutManager as LinearLayoutManager) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                    presenter.loadPhotoList(page)
                }
            }

            addOnScrollListener(infiniteScrollListener)
        }

        contentRefresh?.setOnRefreshListener {
            presenter.loadPhotoList(1)
        }
    }

    override fun showContentList(contentList: MutableList<Photo>) {

        notResultText?.visibility = View.GONE
        contentListView?.visibility = View.VISIBLE

        infiniteScrollListener.resetState()
        showDropDownAnim()
        contentAdapter.loadContent(contentList)
    }

    override fun showDropDownAnim() {
        contentListView?.layoutAnimation = dropDownAnimation
    }

    override fun updateContentList(contentList: MutableList<Photo>) {
        contentAdapter.updateContent(contentList)
    }

    override fun showNotResult() {
        notResultText?.visibility = View.VISIBLE
        contentListView?.visibility = View.GONE
    }

    override fun showProgress() {
        contentRefresh?.isRefreshing = true
    }

    override fun hideProgress() {
        contentRefresh?.isRefreshing = false
    }

    override fun showError(msg: String) {
        activity?.toast(msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun getContext(): Context? {
        return activity
    }

}