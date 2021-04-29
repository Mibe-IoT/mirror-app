package com.mibe.bm.app

import com.mibe.bm.app.controller.AppController
import com.mibe.bm.app.frames.MainFrame
import com.mibe.bm.app.panel.NewsPanel
import com.mibe.bm.app.panel.WeatherPanel
import com.mibe.bm.wi.feed.service.NewsRssService
import com.mibe.bm.wi.files.FilePathService
import com.mibe.bm.wi.files.FilePathServiceImpl

fun main() {
//    val frame = MainFrame()
//    frame.isVisible = true
//    frame.onInit()
//    val newsService = NewsRssService(FilePathServiceImpl())
//    println(newsService.getNews())
    val filePathService: FilePathService = FilePathServiceImpl()
    val panels = listOf(
        NewsPanel(NewsRssService(filePathService)),
        WeatherPanel()
    )
    val controller = AppController(MainFrame(panels))
}
