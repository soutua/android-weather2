package org.roun.weather2.model.data.yrno

import com.squareup.moshi.Json
import java.time.ZonedDateTime

data class ForecastMeta(
    @Json(name = "updated_at") val updatedAt: ZonedDateTime
)