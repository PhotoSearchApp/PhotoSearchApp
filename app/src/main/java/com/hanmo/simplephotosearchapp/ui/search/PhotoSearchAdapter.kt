package com.hanmo.simplephotosearchapp.ui.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hanmo.simplephotosearchapp.CONTENT
import com.hanmo.simplephotosearchapp.KEYWORD
import com.hanmo.simplephotosearchapp.R

/**
 * PhotoSearchAdapter를 생성할때 type을 넘겨준다.
 * Keyword List를 만들때 type : KEYWORD
 * Content List를 만들때 type : CONTENT
 */
class PhotoSearchAdapter(private val type : Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            KEYWORD -> return KeywordHolder(parent)
            CONTENT -> return ContentHolder(parent)
        }
        throw IllegalArgumentException()
    }

    override fun getItemCount(): Int {
        /*when(type) {
            KEYWORD -> return //keyword list count return
            CONTENT -> return //content list count return
        }*/
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is KeywordHolder -> {}
            is ContentHolder -> {}
        }
    }

    override fun getItemViewType(position: Int): Int {
        return type
    }

    inner class KeywordHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_keyword, parent, false)){

    }

    inner class ContentHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false)) {

    }

}