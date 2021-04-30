package com.mibe.bm.wi.web.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.mibe.bm.wi.web.model.IpInfo
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*

class WebClientService(private val ipApiUrl: String) {

    val client: HttpClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = JacksonSerializer {
                disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            }
        }
    }

//    init {
//        properties.load(File(pathService.getFilePath("web.properties")).inputStream())
//        ipUrl = properties.getProperty("api.ip-resolve.url")
//    }

    suspend inline fun <reified T> requestForObject(url: String): T {
        return client.get(url)
    }

    suspend fun getIpInfo(): IpInfo {
        return requestForObject(ipApiUrl)
    }

}