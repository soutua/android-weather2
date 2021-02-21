package org.roun.weather2.model.data.yrno

import com.squareup.moshi.Json

data class ForecastTimePeriodSummary(
    @Json(name = "symbol_code") val symbolCode: String?
)