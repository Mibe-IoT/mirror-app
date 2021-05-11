package com.mibe.bm.cd

import java.io.BufferedReader
import java.io.InputStreamReader

object Command {
    fun exec(command: String): String {
        val processBuilder = ProcessBuilder()
        processBuilder.command("bash", "-c", command)

        val process: Process = processBuilder.start()
        val reader = BufferedReader(InputStreamReader(process.inputStream))
        process.waitFor()
        val line = reader.readText();

        return line;
    }
}