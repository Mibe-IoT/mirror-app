package com.mibe.bm.wi.feed.service

import com.mibe.bm.wi.feed.model.News

interface NewsService {

    fun getNews(): List<News>

}