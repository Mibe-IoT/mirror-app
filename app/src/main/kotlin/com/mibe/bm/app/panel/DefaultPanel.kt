package com.mibe.bm.app.panel

import com.mibe.bm.app.component.JMultilineLabel
import com.mibe.bm.app.service.MessageService
import com.mibe.bm.app.theme.ELEMENT_FONT_CONTENT_SIZE
import com.mibe.bm.app.theme.FONT_COLOR
import java.awt.Font

class DefaultPanel(
    private val messageService: MessageService
) : VerticalAppPanel() {

    val guide: JMultilineLabel

    init {
        val guideText = messageService.getMessage("app.guide")
        guide = JMultilineLabel(guideText).apply {
            foreground = FONT_COLOR
            font = Font(Font.MONOSPACED, Font.PLAIN, ELEMENT_FONT_CONTENT_SIZE)
            border = verticalListMarginBorder
        }
        toggleGuide()
    }

    override fun onAction() {
        toggleGuide()
    }

    private fun toggleGuide(){
        if(components.contains(guide)){
            hideGuide()
        } else {
            showGuide()
        }
        validate()
    }

    private fun showGuide(){
        add(guide)
    }

    private fun hideGuide(){
        removeAll()
    }

}