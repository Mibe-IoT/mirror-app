package com.mibe.bm.app.controller

import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class KeyboardListener(private val callbacks: Callbacks) : KeyAdapter() {

    interface Callbacks {
        fun onCtrlQ() {}
        fun onLeft() {}
        fun onRight() {}
        fun onAction() {}
        fun onHome() {}
        fun onA() {}
        fun onS() {}
    }

    override fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            KeyEvent.VK_LEFT -> callbacks.onLeft()
            KeyEvent.VK_RIGHT -> callbacks.onRight()
            KeyEvent.VK_R -> callbacks.onAction()
            KeyEvent.VK_A -> callbacks.onA()
            KeyEvent.VK_S -> callbacks.onS()
            KeyEvent.VK_H -> callbacks.onHome()
        }
        if (e.isControlDown) {
            if (e.keyCode == KeyEvent.VK_Q) {
                callbacks.onCtrlQ()
            }
        }
    }
}