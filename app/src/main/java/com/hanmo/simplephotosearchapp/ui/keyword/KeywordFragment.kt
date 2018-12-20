package com.hanmo.simplephotosearchapp.ui.keyword

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hanmo.simplephotosearchapp.R
import com.hanmo.simplephotosearchapp.base.BaseFragment
import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class KeywordFragment @Inject constructor() : BaseFragment(), KeywordContract.View {

    @Inject
    lateinit var presenter: KeywordPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_keyword, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}