package com.mibe.bm.app.panel

import com.mibe.bm.app.component.JMultilineLabel
import com.mibe.bm.app.theme.VERTICAL_LIST_GAP
import javax.swing.BoxLayout
import javax.swing.border.EmptyBorder

open class VerticalAppPanel : AppPanel() {

    protected val verticalListMarginBorder = EmptyBorder(0, 0, VERTICAL_LIST_GAP, 0)

    init {
        layout = BoxLayout(this, BoxLayout.Y_AXIS)
    }

    override fun creteTitleLabel(title: String): JMultilineLabel {
        return super.creteTitleLabel(title).apply {
            border = verticalListMarginBorder
        }
    }
}