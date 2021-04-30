package com.mibe.bm.app.service

import java.util.*

class MessageServiceImpl(private var messageSource: ResourceBundle, locale: Locale = Locale.getDefault()) :
    MessageService {

    override fun getMessage(key: String): String {
        return messageSource.getString(key)
    }

    override fun changeLocaleTo(locale: Locale) {
//        println(locale)
        messageSource = ResourceBundle.getBundle(messageSource.baseBundleName, locale)
//        println(messageSource.locale)
    }
}