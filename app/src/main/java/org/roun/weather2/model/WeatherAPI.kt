package org.roun.weather2.model

import org.roun.weather2.model.data.yrno.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("compact")
    suspend fun getForecast(@Query("lat") latitude: Double, @Query("lon") longitude: Double): ForecastResponse

}