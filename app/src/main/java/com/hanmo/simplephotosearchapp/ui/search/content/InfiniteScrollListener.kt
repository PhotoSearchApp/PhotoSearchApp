package com.hanmo.simplephotosearchapp.ui.search.content

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

abstract class InfiniteScrollListener(val layoutManager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var visibleThreshold = 5
    private val startingPage = 1
    private var currentPage = 1
    private var latestTotalItemCount = 0
    private var isLoading = true

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount

        if (totalItemCount < latestTotalItemCount) {
            this.currentPage = this.startingPage
            this.latestTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                isLoading = true
            }
        }

        if (isLoading && totalItemCount > latestTotalItemCount) {
            isLoading = false
            latestTotalItemCount = totalItemCount
        }

        if (!isLoading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            onLoadMore(currentPage, totalItemCount, recyclerView)
            isLoading = true
        }
    }

    fun resetState() {
        this.currentPage = 1
        this.latestTotalItemCount = 0
        this.isLoading = true
        layoutManager.scrollToPositionWithOffset(0, 0)
    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?)
}