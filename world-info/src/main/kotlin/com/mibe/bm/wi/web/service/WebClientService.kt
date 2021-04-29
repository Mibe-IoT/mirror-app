package com.mibe.bm.wi.web.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.mibe.bm.wi.web.model.IpInfo
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*

class WebClientService {

    val client: HttpClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = JacksonSerializer {
                disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            }
        }
    }

    suspend inline fun <reified T> requestForObject(url: String): T {
        return client.get(url)
    }

    suspend fun getIpInfo(): IpInfo {
        return requestForObject<IpInfo>("https://ipapi.co/json")
    }

}

suspend fun main() {
    val service = WebClientService()
//    val ipInfo = service.requestForObject<IpInfo>("https://ipapi.co/json")
    println(service.getIpInfo())
}