package com.mibe.bm.cd

import com.mibe.bm.cd.pairing.service.WifiService
import org.junit.Test

class WifiServiceTest {
    @Test
    fun changeWifiTest() {
        val wifiService = WifiService()
        wifiService.connectToControlDeviceWifi()
    }

    @Test
    fun scanWifiTest() {
        val wifiService = WifiService()
        val list = wifiService.scanWifi()
        list.forEach { print(it) }
    }
}