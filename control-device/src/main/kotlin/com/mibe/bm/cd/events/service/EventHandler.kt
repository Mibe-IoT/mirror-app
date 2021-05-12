package com.mibe.bm.cd.events.service

class EventHandler : EventService.Callbacks {
    override fun onActionButtonPush() {
        println("ACTION")
    }

    override fun onBackButtonPush() {
        println("BACK")
    }

    override fun onForwardButtonPush() {
        println("FORWARD")
    }
}