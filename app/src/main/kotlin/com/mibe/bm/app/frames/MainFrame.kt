package com.mibe.bm.app.frames

import com.mibe.bm.app.panel.AppPanel
import com.mibe.bm.app.panel.DefaultPanel
import java.awt.Dimension
import javax.swing.JFrame
import javax.swing.JPanel

class MainFrame(additionalPanels: List<AppPanel> = listOf()) : JFrame() {

    private val panels: MutableList<AppPanel> = mutableListOf()
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
        size = Dimension(500, 500)
        isUndecorated = true
        isResizable = true
        defaultCloseOperation = EXIT_ON_CLOSE
        panels.addAll(additionalPanels)
        if(panels.isNotEmpty()) {
            contentPane = panels[0]
        }
        setLocationRelativeTo(null)


        isVisible = true
    }

    fun nextPanel() {
        changePanelTo(nextPanel)
    }

    fun previousPanel() {
        changePanelTo(previousPanel)
    }

    fun goHome() {
        if(panels.isNotEmpty()){
            currentPanel = 0
            changePanelTo(panels[currentPanel])
        }
    }

    fun doAction() {
        panels[currentPanel].onAction()
    }

    fun doUpdate() {
        panels[currentPanel].onUpdate()
    }

    private fun changePanelTo(panel: JPanel) {
        contentPane = panel
        validate()
    }

}