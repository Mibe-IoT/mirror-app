package com.mibe.bm.cd.events.dto

import kotlinx.serialization.Serializable

@Serializable
data class ButtonDTO(
    val type: String,
    val value: String
)