package com.mibe.bm.app.panel

import com.mibe.bm.app.component.JMultilineLabel
import com.mibe.bm.app.service.MessageService
import com.mibe.bm.app.theme.ELEMENT_FONT_CONTENT_SIZE
import com.mibe.bm.app.theme.FONT_COLOR
import com.mibe.bm.wi.feed.model.News
import com.mibe.bm.wi.feed.service.NewsService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.awt.Font

class NewsPanel(
    private val messageService: MessageService,
    private val newsService: NewsService,
) : VerticalAppPanel() {

    val NEWS_AMOUNT = 9L

    private var newsList: List<News> = listOf()
    private val panelTitle = AppPanelType.NEWS.asciiArt
    private val title: JMultilineLabel = creteTitleLabel(panelTitle)

    init {
        loadNews()
        redraw()
        isVisible = true
    }

    override fun onAction() {
        onUpdate()
    }

    override fun onUpdate() {
        loadNews()
    }

    private fun loadNews() {
        GlobalScope.launch {
            newsList = try {
                newsService.getNews(NEWS_AMOUNT)
            } catch (e: Exception) {
                listOf()
            }
        }.invokeOnCompletion {
            redraw()
        }
    }

    private fun redraw() {
        removeAll()
        add(title)
        newsList.ifEmpty {
            add(createJMultilineLabel(messageService.getMessage("error.no_internet_connection")))
        }
        newsList.forEach { addNewsRow(it) }
        validate()
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