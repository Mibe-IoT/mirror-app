package com.mibe.bm.cd.events.service

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*

class EventService(private val ip: String) {
    private val client = HttpClient(CIO) {
        install(WebSockets)
    }

    interface Callbacks {
        fun onForwardButtonPush()
        fun onBackButtonPush()
        fun onActionButtonPush()
        fun onMetricReceived()
    }

    suspend fun connect() {
        client.ws(
            method = HttpMethod.Get,
            host = ip,
            port = 81,
            path = "/"
        ) {
            send(Frame.Text("{\"type\":\"metric\"}"))
            when (val frame = incoming.receive()) {
                is Frame.Text -> println(frame.readText())
            }
        }
    }
}