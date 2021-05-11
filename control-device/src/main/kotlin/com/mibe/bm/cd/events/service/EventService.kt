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

class EventService(private val ip: String, private val callbacks: Callbacks) {
    private val client = HttpClient(CIO) {
        install(WebSockets)
    }

    interface Callbacks {
        fun onForwardButtonPush() {}
        fun onBackButtonPush() {}
        fun onActionButtonPush() {}
        fun onMetricReceived(metric: MetricDTO) {}
    }



    suspend fun connect() {
        client.ws(
            method = HttpMethod.Get,
            host = ip,
            port = 81,
            path = "/"
        ) {
            while (true) {
                incoming.consumeAsFlow()
                    .mapNotNull { it as? Frame.Text }
                    .map { it.readText() }
                    .map {
                        if (it.matches(".+button.+".toRegex())) {
                            val buttonPushed = Json.decodeFromString<ButtonDTO>(it)
                            when (buttonPushed.value) {
                                "back" -> callbacks.onBackButtonPush()
                                "forward" -> callbacks.onForwardButtonPush()
                                "action" -> callbacks.onActionButtonPush()
                            }
                        }
                        if (it.matches(".+metric.+".toRegex())) {
                            val metricReceived = Json.decodeFromString<MetricDTO>(it)
                            callbacks.onMetricReceived(metricReceived)
                        }
                    }
            }
        }
    }
}