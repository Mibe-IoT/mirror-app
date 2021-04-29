package com.mibe.bm.app.controller

import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class KeyboardListener(private val callbacks: Callbacks) : KeyAdapter() {

    interface Callbacks {
        fun onCtrlQ() {}
        fun onLeft() {}
        fun onRight() {}
        fun onAction() {}
    }

    override fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            KeyEvent.VK_LEFT -> callbacks.onLeft()
            KeyEvent.VK_RIGHT -> callbacks.onRight()
            KeyEvent.VK_R -> callbacks.onAction()
        }
        if (e.isControlDown) {
            if (e.keyCode == KeyEvent.VK_Q) {
                callbacks.onCtrlQ()
            }
        }
    }
}