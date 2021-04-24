package com.mibe.bm.app.frames

import com.mibe.bm.app.panel.NewsPanel
import com.mibe.bm.app.panel.WeatherPanel
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.lang.Thread.sleep
import javax.swing.JFrame

class MainFrame : JFrame(), KeyListener {

    init {
        extendedState = MAXIMIZED_BOTH
        isUndecorated = true
        defaultCloseOperation = EXIT_ON_CLOSE
        contentPane = WeatherPanel()
        addKeyListener(this)
    }

    fun onInit() {
        sleep(1000)
        contentPane = NewsPanel()
        contentPane.revalidate()
        sleep(1000)
        contentPane = WeatherPanel()
        contentPane.revalidate()
    }

    override fun keyTyped(p0: KeyEvent?) {
        println(p0)
    }

    override fun keyPressed(p0: KeyEvent?) {
    }

    override fun keyReleased(p0: KeyEvent?) {
    }
}