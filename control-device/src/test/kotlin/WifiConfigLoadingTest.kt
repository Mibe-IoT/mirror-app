package com.mibe.bm.cd

import com.mibe.bm.cd.pairing.service.CONFIG_FILE
import com.mibe.bm.cd.pairing.specs.ControlDeviceWifiSpec
import com.mibe.bm.cd.pairing.specs.HomeWifiSpec
import com.uchuhimo.konf.Config
import com.uchuhimo.konf.source.yaml
import org.junit.Test
import kotlin.test.assertEquals

class WifiConfigLoadingTest {
    @Test
    fun homeWifiConfigTesting() {
        val homeWifiConfig = Config { addSpec(HomeWifiSpec) }
            .from.yaml.resource(CONFIG_FILE)
            .at("homeWifi")

        assertEquals(homeWifiConfig.get<String>("ssid"), "Mac")
    }

    @Test
    fun controlDeviceWifiConfigTesting() {
        val controlDeviceWifiConfig = Config { addSpec(ControlDeviceWifiSpec) }
            .from.yaml.resource(CONFIG_FILE)
            .at("controlDeviceWifi")

        assertEquals(controlDeviceWifiConfig.get<String>("ssid"), "No-Mac")
    }
}