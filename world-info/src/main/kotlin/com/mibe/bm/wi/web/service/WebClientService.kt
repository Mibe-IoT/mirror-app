package com.mibe.bm.wi.web.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.mibe.bm.wi.files.FilePathService
import com.mibe.bm.wi.files.FilePathServiceImpl
import com.mibe.bm.wi.web.model.IpInfo
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*
import java.io.File
import java.util.*

class WebClientService(
    private val pathService: FilePathService
) {

    private val properties = Properties()
    private val ipUrl: String

    val client: HttpClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = JacksonSerializer {
                disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            }
        }
    }

    init {
        properties.load(File(pathService.getFilePath("web.properties")).inputStream())
        ipUrl = properties.getProperty("url.ip-resolve")
    }

    suspend inline fun <reified T> requestForObject(url: String): T {
        return client.get(url)
    }

    suspend fun getIpInfo(): IpInfo {
        properties.entries.forEach { println(it) }
        return requestForObject(ipUrl)
    }

}

suspend fun main() {
    val service = WebClientService(FilePathServiceImpl())
    println(service.getIpInfo())
}