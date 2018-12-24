package com.hanmo.simplephotosearchapp.ui.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hanmo.simplephotosearchapp.CONTENT
import com.hanmo.simplephotosearchapp.KEYWORD
import com.hanmo.simplephotosearchapp.R
import com.hanmo.simplephotosearchapp.data.realm.RealmService
import com.hanmo.simplephotosearchapp.model.Photo
import kotlinx.android.synthetic.main.item_content.view.*
import kotlinx.android.synthetic.main.item_keyword.view.*

/**
 * PhotoSearchAdapter를 생성할때 type을 넘겨준다.
 * Keyword List를 만들때 type : KEYWORD
 * Content List를 만들때 type : CONTENT
 */
class PhotoSearchAdapter(private val type : Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var keywordList : MutableList<String> = mutableListOf()
    private var contentList : MutableList<Photo> = mutableListOf()

    private lateinit var itemClickListener : OnItemClickListener

    fun setOnItemClickListener(itemClickListener : OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface OnItemClickListener {
        fun onItemClick(keywordName: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            KEYWORD -> return KeywordHolder(parent)
            CONTENT -> return ContentHolder(parent)
        }
        throw IllegalArgumentException()
    }

    override fun getItemCount(): Int {
        when(type) {
            KEYWORD -> return keywordList.size //keyword list count return
            CONTENT -> return contentList.size //content list count return
        }
        return 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is KeywordHolder -> holder.onBind(keywordList[position])
            is ContentHolder -> holder.onBind(contentList[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return type
    }

    fun loadKeyword() {
        RealmService.getKeywordList()?.forEach { keyword ->
            keyword.name?.let { name -> keywordList.add(name) }
        }
        notifyDataSetChanged()
    }

    fun loadContent(contentList : MutableList<Photo>) {
        this.contentList = contentList
        notifyDataSetChanged()
    }

    fun updateContent(contentList : MutableList<Photo>) {
        this.contentList.addAll(contentList)
        notifyDataSetChanged()
    }

    inner class KeywordHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_keyword, parent, false)), View.OnClickListener{

        init {
            itemView.keyword.setOnClickListener(this)
        }

        fun onBind(keywordName: String) {
            itemView?.run {
                keyword.text = keywordName
            }
        }

        override fun onClick(v: View?) {
            itemClickListener.onItemClick(keywordList[adapterPosition])
        }
    }

    inner class ContentHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false)) {

        fun onBind(photo: Photo) {
            itemView?.run {
                val imageUrl = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"
                Glide.with(context).load(imageUrl).apply(RequestOptions().fitCenter()).thumbnail(0.1f).into(photoImage)
                photoTitle.text = photo.title
            }
        }
    }

}