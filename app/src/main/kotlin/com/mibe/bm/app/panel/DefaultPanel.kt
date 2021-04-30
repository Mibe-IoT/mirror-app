package com.mibe.bm.app.panel

import com.mibe.bm.app.service.MessageService

class DefaultPanel(
    messageService: MessageService
) : VerticalAppPanel(messageService)