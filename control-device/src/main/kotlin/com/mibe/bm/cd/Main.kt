package com.mibe.bm.cd

import com.mibe.bm.cd.pairing.service.PairingService
import kotlin.system.measureTimeMillis

suspend fun main() {
    val time = measureTimeMillis {
        val pairingService = PairingService()
        val ip = pairingService.pairing()
        println("Device ip: $ip")
    }
}