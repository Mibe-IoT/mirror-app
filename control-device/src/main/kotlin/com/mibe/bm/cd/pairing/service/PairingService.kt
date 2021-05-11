package com.mibe.bm.cd.pairing.service

import com.mibe.bm.cd.pairing.dto.PairingDTO
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.request.*

class PairingService {
    private val wifiService = WifiService()

    private val client = HttpClient(CIO) {
        install(JsonFeature)
        install(HttpTimeout) {
            requestTimeoutMillis = 1000000
        }
    }

    private fun findControlDevice() {
        val controlDeviceConfig = wifiService.getControlDeviceWifiConfig()
        while (true) {
            val wifiList = wifiService.scanWifi()
            wifiList.forEach {
                if (it == controlDeviceConfig.get<String>("ssid")) {
                    return;
                }
            }
        }
    }

    suspend fun pairing(): String {
        findControlDevice()
        println("Control device Wifi found")
        wifiService.connectToControlDeviceWifi()
        println("Connected to control device")

        val homeWifiConfig = wifiService.homeWifiConfig()
        val response = client.get<PairingDTO>(
            "http://192.168.1.4/" +
                    "ssid/${homeWifiConfig.get<String>("ssid")}/" +
                    "password/${homeWifiConfig.get<String>("password")}"
        )
        if (response.status == "unauthorized")
            throw RuntimeException("Cannot connect control device to Wifi")

        println("Control device connected!")

        wifiService.connectToHomeWifi()

        return response.status
    }
}