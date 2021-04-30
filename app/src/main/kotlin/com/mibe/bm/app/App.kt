package com.mibe.bm.app

import com.mibe.bm.app.controller.AppController
import com.mibe.bm.app.frames.MainFrame
import com.mibe.bm.app.panel.NewsPanel
import com.mibe.bm.app.panel.WeatherPanel
import com.mibe.bm.app.service.MessageServiceImpl
import com.mibe.bm.wi.feed.service.NewsRssService
import com.mibe.bm.wi.files.FilePathService
import com.mibe.bm.wi.files.FilePathServiceImpl
import com.mibe.bm.wi.weather.controller.WeatherController
import java.util.*

fun main() {
    val filePathService: FilePathService = FilePathServiceImpl()
    val messageSource = ResourceBundle.getBundle("messages")
    val messageService = MessageServiceImpl(messageSource)
    val panels = listOf(
        NewsPanel(messageService, NewsRssService(filePathService)),
        WeatherPanel(messageService, WeatherController(filePathService))
    )
    val controller = AppController(MainFrame(panels))
}
