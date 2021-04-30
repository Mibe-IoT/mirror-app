package com.mibe.bm.app.panel

import com.mibe.bm.app.component.JMultilineLabel
import com.mibe.bm.app.service.MessageService
import com.mibe.bm.app.theme.BORDER_PADDING
import com.mibe.bm.app.theme.DEFAULT_BG
import com.mibe.bm.app.theme.DEFAULT_FONT
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.JPanel
import javax.swing.border.Border

open class AppPanel(
    protected val messageService: MessageService
) : JPanel() {

    protected val font = DEFAULT_FONT

    protected val borderPadding: Border = BorderFactory.createEmptyBorder(
        BORDER_PADDING - 5, BORDER_PADDING,
        BORDER_PADDING, BORDER_PADDING
    )

    init {
        border = borderPadding
        isVisible = true
        background = DEFAULT_BG
    }

    protected fun createJMultilineLabel(text: String): JMultilineLabel {
        return JMultilineLabel(text).apply {
            foreground = com.mibe.bm.app.theme.FONT_COLOR
            font = Font(Font.MONOSPACED, Font.BOLD, com.mibe.bm.app.theme.ELEMENT_FONT_CONTENT_SIZE)
        }
    }

    open fun creteTitleLabel(title: String): JMultilineLabel {
        return JMultilineLabel(title).apply {
            font = Font(Font.MONOSPACED, Font.BOLD, com.mibe.bm.app.theme.ELEMENT_FONT_TITLE_SIZE)
            foreground = com.mibe.bm.app.theme.FONT_COLOR
        }
    }

    open fun onAction() {}
    open fun onUpdate() {}

}