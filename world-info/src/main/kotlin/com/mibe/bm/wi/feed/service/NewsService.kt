package com.mibe.bm.wi.feed.service

import com.mibe.bm.wi.feed.model.News

interface NewsService {

    fun getNews(amount: Long = 10L, contentMaxLength: Int = 255): List<News>

}