package com.hanmo.simplephotosearchapp.ui.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hanmo.simplephotosearchapp.R
import com.hanmo.simplephotosearchapp.base.BaseFragment
import com.hanmo.simplephotosearchapp.di.annotation.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ContentFragment @Inject constructor() : BaseFragment(), ContentContract.View {

    @Inject
    lateinit var presenter: ContentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        presenter.takeView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

}