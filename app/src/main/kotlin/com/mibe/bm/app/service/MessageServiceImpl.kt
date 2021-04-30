package com.mibe.bm.app.service

import java.util.*

class MessageServiceImpl(private val messageSource: ResourceBundle, locale: Locale = Locale.getDefault()) :
    MessageService {

    var currentLocale: Locale = locale

    override fun getMessage(key: String): String {
        return messageSource.getString(key)
    }

}