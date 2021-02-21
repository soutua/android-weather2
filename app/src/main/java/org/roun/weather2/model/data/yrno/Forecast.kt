package org.roun.weather2.model.data.yrno

data class Forecast(
    val timeseries: List<ForecastTimeSeries>,
    val meta: ForecastMeta
)