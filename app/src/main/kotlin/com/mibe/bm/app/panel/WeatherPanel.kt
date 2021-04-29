package com.mibe.bm.app.panel

import com.mibe.bm.app.component.JMultilineLabel
import com.mibe.bm.wi.feed.service.NewsService

class WeatherPanel(
    private val newsService: NewsService,
) : VerticalAppPanel() {

    private val panelTitle = AppPanelType.WEATHER.asciiArt
    private val title: JMultilineLabel

    init {
        title = creteTitleLabel(panelTitle)
        redraw()
        isVisible = true
    }

    override fun onAction() {
        redraw()
        validate()
    }

    private fun redraw() {
        removeAll()
        add(title)
    }

}