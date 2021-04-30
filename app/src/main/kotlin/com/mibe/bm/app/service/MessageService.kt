package com.mibe.bm.app.service

import java.util.*

interface MessageService {

    fun getMessage(key: String): String
    fun changeLocaleTo(locale: Locale)
}