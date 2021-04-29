package com.mibe.bm.app.controller

import com.mibe.bm.app.frames.MainFrame
import java.awt.GraphicsEnvironment
import kotlin.system.exitProcess

class AppController(
    private val mainFrame: MainFrame,
) : KeyboardListener.Callbacks {

    private val keyboardListener: KeyboardListener = KeyboardListener(this)

    init {
        mainFrame.addKeyListener(keyboardListener)

        //Code bellow enters fullscreen hardly
        val graphics = GraphicsEnvironment.getLocalGraphicsEnvironment()
        val device = graphics.defaultScreenDevice
        device.fullScreenWindow = mainFrame
    }

    override fun onCtrlQ() {
        exitProcess(0)
    }

    override fun onLeft() {
        mainFrame.previousPanel()
    }

    override fun onRight() {
        mainFrame.nextPanel()
    }

    override fun onAction() {
        mainFrame.doAction()
    }
}