package com.mibe.bm.cd

import com.mibe.bm.cd.pairing.service.WifiService
import org.junit.Test

class WifiServiceTest {
    @Test
    fun shellCommandTest() {
        val wifiService = WifiService()
        val shellCommand = wifiService.shellScript()
        print(shellCommand)
    }

    @Test
    fun changeWifiTest() {
        val wifiService = WifiService()
        wifiService.connectToControlDeviceWifi()
    }
}