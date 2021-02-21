package org.roun.weather2.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.roun.weather2.model.data.yrno.ForecastResponse
import java.time.ZonedDateTime

@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey val id: Int,
    val time: ZonedDateTime,
    val temperature: Double?,
    @ColumnInfo(name = "wind_speed") val windSpeed: Double?,
    @ColumnInfo(name = "precipitation_amount") val precipitationAmount: Double?,
    @ColumnInfo(name = "symbol_code") val symbolCode: String?
) {
    companion object {
        fun from(forecastResponse: ForecastResponse): Weather = Weather(
            0,
            forecastResponse.properties.meta.updatedAt,
            forecastResponse.properties.timeseries[0].data.instant?.details?.airTemperature,
            forecastResponse.properties.timeseries[0].data.instant?.details?.windSpeed,
            forecastResponse.properties.timeseries[0].data.next1Hour?.details?.precipitationAmount,
            forecastResponse.properties.timeseries[0].data.next1Hour?.summary?.symbolCode
        )
    }
}