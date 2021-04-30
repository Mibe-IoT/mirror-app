package com.mibe.bm.app.frames

import com.mibe.bm.app.panel.AppPanel
import com.mibe.bm.app.panel.DefaultPanel
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JPanel

class MainFrame(additionalPanels: List<AppPanel> = listOf()) : JFrame() {

    private val panels: MutableList<AppPanel> = mutableListOf(DefaultPanel())
    private var currentPanel = 0

    private val nextPanel: AppPanel
        get() {
            if (++currentPanel >= panels.size) {
                currentPanel = 0
            }
            return panels[currentPanel]
        }

    private val previousPanel: AppPanel
        get() {
            if (--currentPanel < 0) {
                currentPanel = panels.size - 1
            }
            return panels[currentPanel]
        }

    init {
//        extendedState = MAXIMIZED_BOTH
        size = Dimension(500, 500)
        isUndecorated = true
        isResizable = true
        defaultCloseOperation = EXIT_ON_CLOSE
        contentPane = panels[0]
        setLocationRelativeTo(null)
        panels.addAll(additionalPanels)


        isVisible = true
    }

    fun nextPanel() {
        changePanelTo(nextPanel)
    }

    fun previousPanel() {
        changePanelTo(previousPanel)
    }

    fun doAction() {
        panels[currentPanel].onAction()
    }

    private fun changePanelTo(panel: JPanel) {
        contentPane = panel
        validate()
    }

}