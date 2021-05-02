package com.mibe.bm.cd.pairing.service

import com.mibe.bm.cd.pairing.specs.ControlDeviceWifiSpec
import com.mibe.bm.cd.pairing.specs.HomeWifiSpec
import com.uchuhimo.konf.Config
import com.uchuhimo.konf.source.yaml
import java.nio.file.Paths

const val CONFIG_FILE = "control-device-config.yaml"
const val SCRIPT_NAME = "connect-to-wifi.sh"

class WifiService {
    private val homeWifiConfig: Config = Config { addSpec(HomeWifiSpec) }
        .from.yaml.resource(CONFIG_FILE)
        .at("homeWifi")

    private val controlDeviceWifiConfig: Config = Config { addSpec(ControlDeviceWifiSpec) }
        .from.yaml.resource(CONFIG_FILE)
        .at("controlDeviceWifi")

    fun shellScript(): String {
        return Paths.get(ClassLoader.getSystemResource(SCRIPT_NAME).toURI()).toString()
    }

    private fun rulesToExecute(programPath: String) {
        Runtime.getRuntime().exec("chmod +x $programPath")
    }

    private fun setArgs(command: String, vararg args: String): String {
        return command + " " + args.joinToString(" ")
    }

    fun connectToControlDeviceWifi() {
        val ssid = controlDeviceWifiConfig.get<String>("ssid")
        val password = controlDeviceWifiConfig.get<String>("password")
        val programPath = shellScript()
        rulesToExecute(programPath)
        Runtime.getRuntime().exec(setArgs(programPath, ssid, password))
    }
}