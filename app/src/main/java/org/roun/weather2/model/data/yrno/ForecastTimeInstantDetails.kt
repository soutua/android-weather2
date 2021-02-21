package org.roun.weather2.model.data.yrno

import com.squareup.moshi.Json

data class ForecastTimeInstantDetails(
    @Json(name = "air_temperature") val airTemperature: Double?,
    @Json(name = "relative_humidity") val relativeHumidity: Double?,
    @Json(name = "wind_speed_of_gust") val gustWindSpeed: Double?,
    @Json(name = "wind_speed") val windSpeed: Double?
)