package com.mibe.bm.cd.pairing.service

import com.mibe.bm.cd.pairing.specs.ControlDeviceWifiSpec
import com.mibe.bm.cd.pairing.specs.HomeWifiSpec
import com.uchuhimo.konf.Config
import com.uchuhimo.konf.source.yaml
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.lang.RuntimeException
import java.nio.file.Paths

const val CONFIG_FILE = "control-device-config.yaml"
const val CONFIG_NAME = "wpa_supplicant.conf"

class WifiService {
    private val homeWifiConfig: Config = Config { addSpec(HomeWifiSpec) }
        .from.yaml.resource(CONFIG_FILE)
        .at("homeWifi")

    private val controlDeviceWifiConfig: Config = Config { addSpec(ControlDeviceWifiSpec) }
        .from.yaml.resource(CONFIG_FILE)
        .at("controlDeviceWifi")

    fun getControlDeviceWifiConfig(): Config {
        return controlDeviceWifiConfig
    }

    fun homeWifiConfig(): Config {
        return homeWifiConfig
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
        connectToWifi(ssid, password)
    }

    fun connectToHomeWifi() {
        val ssid = homeWifiConfig.get<String>("ssid")
        val password = homeWifiConfig.get<String>("password")
        connectToWifi(ssid, password)
    }

    private fun connectToWifi(ssid: String, password: String) {
        val configSample =
            "network={\n" +
                    "ssid=\"$ssid\"\n" +
                    "scan_ssid=1\n" +
                    "psk=\"$password\"\n" +
                    "key_mgmt=WPA-PSK\n" +
                    "}"

        val scriptBody =
            "/sbin/ifconfig wlan0 down\n" +
                    "sleep 1\n" +
                    "killall wpa_supplicant\n" +
                    "sleep 1\n" +
                    "/sbin/ifconfig wlan0 up\n" +
                    "sleep 1\n" +
                    "/sbin/wpa_supplicant -B -c$CONFIG_NAME -iwlan0\n" +
                    "sleep 20"

        File(CONFIG_NAME).writeText(configSample)

        val processBuilder = ProcessBuilder()
        processBuilder.command("bash", "-c", scriptBody)

        val process: Process = processBuilder.start()
        val exitCode = process.waitFor()
        if (exitCode != 0) {
            throw RuntimeException("Cannot connect to Wifi")
        }
    }

    fun scanWifi(): List<String> {
        val scanCommand = "iwlist wlan0 scan | grep 'ESSID:' | sed -E \"s/(.+):\\\"(.+)\\\"/\\2/\""
        val processBuilder = ProcessBuilder()
        processBuilder.command("bash", "-c", scanCommand)
        val process: Process = processBuilder.start()
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        process.waitFor()
        val line = reader.readText()
        return line.split('\n')
    }
}