package com.mibe.bm.app.panel

import com.mibe.bm.app.theme.BORDER_PADDING
import com.mibe.bm.app.theme.DEFAULT_BG
import com.mibe.bm.app.theme.DEFAULT_FONT
import javax.swing.BorderFactory
import javax.swing.JPanel
import javax.swing.border.Border

open class AppPanel : JPanel() {

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

    fun decorateTitle(title: String): String {
        return title
    }

    open fun onAction() {}

}