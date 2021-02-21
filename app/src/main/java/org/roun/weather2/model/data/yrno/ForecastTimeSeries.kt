package org.roun.weather2.model.data.yrno

import java.time.ZonedDateTime

data class ForecastTimeSeries(
    val time: ZonedDateTime,
    val data: ForecastData
)