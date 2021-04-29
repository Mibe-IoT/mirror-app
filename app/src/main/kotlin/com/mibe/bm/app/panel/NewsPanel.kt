package com.mibe.bm.app.panel

import com.mibe.bm.app.component.JMultilineLabel
import com.mibe.bm.app.theme.DEFAULT_BG
import com.mibe.bm.app.theme.ELEMENT_FONT_CONTENT_SIZE
import com.mibe.bm.app.theme.ELEMENT_FONT_TITLE_SIZE
import com.mibe.bm.app.theme.FONT_COLOR
import com.mibe.bm.wi.feed.model.News
import com.mibe.bm.wi.feed.service.NewsService
import java.awt.Font

const val NEWS_AMOUNT = 9L

class NewsPanel(
    private val newsService: NewsService,
) : VerticalAppPanel() {

    private var newsList: List<News> = listOf()
    private val panelTitle = AppPanelType.NEWS.asciiArt
    private val title: JMultilineLabel

    init {
        border = borderPadding
        background = DEFAULT_BG
        title = JMultilineLabel(panelTitle).apply {
            font = Font(Font.MONOSPACED, Font.BOLD, ELEMENT_FONT_TITLE_SIZE)
            foreground = FONT_COLOR
            border = verticalListMarginBorder
        }
        loadNews()
        redraw()
        isVisible = true
    }

    override fun onAction() {
        loadNews()
        redraw()
        validate()
    }

    fun loadNews() {
        newsList = newsService.getNews(NEWS_AMOUNT)
    }

    private fun redraw() {
        removeAll()
        add(title)
        newsList.forEach { addNewsRow(it) }
    }

    private fun addNewsRow(news: News) {
        val title = JMultilineLabel(news.title).apply {
            foreground = FONT_COLOR
            font = Font(Font.MONOSPACED, Font.BOLD, ELEMENT_FONT_CONTENT_SIZE)
        }
        val date = JMultilineLabel(news.date).apply {
            foreground = FONT_COLOR
            font = Font(Font.MONOSPACED, Font.PLAIN, ELEMENT_FONT_CONTENT_SIZE)
        }
        val content = JMultilineLabel(news.content).apply {
            foreground = FONT_COLOR
            font = Font(Font.MONOSPACED, Font.PLAIN, ELEMENT_FONT_CONTENT_SIZE)
            border = verticalListMarginBorder
        }
        add(title)
        add(date)
        add(content)
    }

}