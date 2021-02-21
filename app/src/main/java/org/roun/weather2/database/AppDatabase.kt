package org.roun.weather2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.roun.weather2.model.data.Weather

@Database(
    entities = [Weather::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}