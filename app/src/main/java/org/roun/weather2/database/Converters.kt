package org.roun.weather2.database

import androidx.room.TypeConverter
import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime

class Converters {

    @TypeConverter
    fun longToZonedDateTime(value: Long?): ZonedDateTime? = value?.let { ZonedDateTime.from(Instant.ofEpochSecond(it).atOffset(ZoneOffset.UTC)) }

    @TypeConverter
    fun zonedDateTimeToLong(time: ZonedDateTime?): Long? = time?.toInstant()?.epochSecond

}