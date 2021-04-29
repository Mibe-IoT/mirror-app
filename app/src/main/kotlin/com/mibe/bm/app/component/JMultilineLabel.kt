package com.mibe.bm.app.component

import javax.swing.JTextArea


class JMultilineLabel(text: String?) : JTextArea(text) {
    companion object {
        private const val serialVersionUID = 1L
    }

    init {
        isEditable = false
        cursor = null
        isOpaque = false
        isFocusable = true
        wrapStyleWord = true
        lineWrap = true
        alignmentY = LEFT_ALIGNMENT
    }
}