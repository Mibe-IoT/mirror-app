package com.mibe.bm.cd.events.service

import com.mibe.bm.cd.events.dto.ButtonDTO
import com.mibe.bm.cd.events.dto.MetricDTO
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.websocket.*
import io.ktor.http.*
import io.ktor.http.cio.websocket.*
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

class EventService(
    private val ip: String,
    private val connectedCallback: (session: WebSocketSession) -> Unit,
    private val callbacks: Callbacks,
    private val metricCallback: (metric: MetricDTO) -> Unit
) {
    private val client = HttpClient(CIO) {
        install(WebSockets)
    }

    lateinit var session: WebSocketSession

    suspend fun requestMetrics() {
        val request = "{\"type\": \"metric\"}"
        session.send(request)
    }

    interface Callbacks {
        fun onForwardButtonPush() {}
        fun onBackButtonPush() {}
        fun onActionButtonPush() {}
    }

    suspend fun connect() {
        client.ws(
            method = HttpMethod.Get,
            host = ip,
            port = 81,
            path = "/"
        ) {
            connectedCallback(this)
            session = this
            while (true) {
                when (val frame = incoming.receive()) {
                    is Frame.Text -> {
                        val message = frame.readText()
                        println(message)
                        if (message.matches(".+button.+".toRegex())) {
                            val buttonPushed = Json.decodeFromString<ButtonDTO>(message)
                            when (buttonPushed.value) {
                                "back" -> callbacks.onBackButtonPush()
                                "forward" -> callbacks.onForwardButtonPush()
                                "action" -> callbacks.onActionButtonPush()
                            }
                        }
                        if (message.matches(".+metric.+".toRegex())) {
                            val metricReceived = Json.decodeFromString<MetricDTO>(message)
                            metricCallback(metricReceived)
                        }
                    }
                }
            }
        }
    }
}