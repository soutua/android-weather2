package org.roun.weather2.model

import android.location.Location
import android.os.SystemClock
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import org.roun.weather2.database.WeatherDao
import org.roun.weather2.model.data.Weather
import org.roun.weather2.model.data.yrno.ForecastResponse
import timber.log.Timber
import javax.inject.Inject

private const val UPDATE_FREQUENCY_MS = 600_000

class WeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao,
    private val locationProvider: FusedLocationProviderClient
) {
    private var lastWeatherCheckTime: Long = 0

    fun weatherDataInCurrentLocation(): Flow<Weather> = weatherDao.get()

    suspend fun refresh() {
        val currentTime = SystemClock.elapsedRealtime()

        if (currentTime - lastWeatherCheckTime > UPDATE_FREQUENCY_MS) {
            lastWeatherCheckTime = currentTime
            var location: Location? = null

            try {
                location = locationProvider.getCurrentLocation(LocationRequest.PRIORITY_LOW_POWER, null).await()
            } catch (e: SecurityException) {
                lastWeatherCheckTime = 0
            }

            if (location != null) {
                try {
                    val forecast: ForecastResponse = weatherApi.getForecast(location.latitude, location.longitude)
                    weatherDao.save(Weather.from(forecast))
                } catch (e: Exception) {
                    Timber.e(e)
                    lastWeatherCheckTime = 0
                }
            }
        }
    }
}