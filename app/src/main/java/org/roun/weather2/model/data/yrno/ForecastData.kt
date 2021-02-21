package org.roun.weather2.model.data.yrno

import com.squareup.moshi.Json

data class ForecastData(
        val instant: ForecastTimeInstant?,
        @Json(name = "next_1_hours") val next1Hour: ForecastTimePeriod?
)