package com.mibe.bm.wi.feed.service

import com.apptastic.rssreader.RssReader
import com.mibe.bm.wi.feed.model.News
import org.jsoup.Jsoup
import java.io.File
import java.util.stream.Collectors

class NewsRssService(
    private val pathService: FilePathService
) : NewsService {

    private val reader: RssReader = RssReader()

    override fun getNews(): List<News> {
        val inputStream = File(pathService.getFilePath("newsUrls.txt")).inputStream()
        val urls = inputStream.bufferedReader().lines().collect(Collectors.toList())
        return urls.stream().map { reader.read(it) }
            .flatMap { it }
            .limit(10)
            .map {
                News(
                    it.title.get(), it.pubDate.get(),
                    Jsoup.parse(it.description.get()).text()
                )
            }
            .collect(Collectors.toList())
    }

}