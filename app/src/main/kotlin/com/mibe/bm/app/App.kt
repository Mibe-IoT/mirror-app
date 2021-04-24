package com.mibe.bm.app

import com.mibe.bm.wi.feed.service.FilePathServiceImpl
import com.mibe.bm.wi.feed.service.NewsRssService

fun main() {
//    val frame = MainFrame()
//    frame.isVisible = true
//    frame.onInit()
    val newsService = NewsRssService(FilePathServiceImpl())
    println(newsService.getNews())
}