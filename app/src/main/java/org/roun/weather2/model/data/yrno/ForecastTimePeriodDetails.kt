package org.roun.weather2.model.data.yrno

import com.squareup.moshi.Json

data class ForecastTimePeriodDetails(
    @Json(name = "air_temperature_min") val airTemperatureMin: Double?,
    @Json(name = "air_temperature_max") val airTemperatureMax: Double?,
    @Json(name = "precipitation_amount") val precipitationAmount: Double?,
    @Json(name = "probability_of_precipitation") val probabilityOfPrecipitation: Double?
)