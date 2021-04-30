package com.mibe.bm.wi.feed.service

import com.apptastic.rssreader.RssReader
import com.mibe.bm.wi.feed.model.News
import com.mibe.bm.wi.files.FilePathService
import org.jsoup.Jsoup
import java.util.*
import java.util.stream.Collectors

class NewsRssService(
    private val pathService: FilePathService,
) : NewsService {

    private val reader: RssReader = RssReader()

    override fun getNews(amount: Long, contentMaxLength: Int): List<News> {
        val urls = ResourceBundle.getBundle("news").keySet()
        val news = urls.stream().map { reader.read(it) }
            .flatMap { it.limit(amount) }
            .limit(amount * 2)
            .filter {
                it.run {
                    title.isPresent && pubDate.isPresent && description.isPresent
                }
            }
            .map {
                var content = Jsoup.parse(it.description.orElse("")).text()
                if (content.length > contentMaxLength) {
                    content = content.substring(0, contentMaxLength) + "..."
                }
                News(
                    it.title.orElse(""), it.pubDate.orElse(""),
                    content
                )
            }
            .collect(Collectors.toList())
            .shuffled()
        return if (news.isEmpty())
            news
        else
            news.subList(0, (amount - 1).toInt())
                .sortedBy { it.date }
    }

}