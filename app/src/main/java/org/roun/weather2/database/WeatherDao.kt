package org.roun.weather2.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.roun.weather2.model.data.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(weather: Weather)

    @Query("SELECT * FROM weather WHERE id = 0")
    fun get(): Flow<Weather>

}