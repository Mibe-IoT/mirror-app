package com.mibe.bm.cd.events.dto

import kotlinx.serialization.Serializable

@Serializable
data class MetricDTO(
    val type: String,
    val temperature: Float,
    val humidity: Float
)
