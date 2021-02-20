package io.github.agimaulana.monity.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.agimaulana.monity.converter.TimestampConverter
import io.github.agimaulana.monity.dao.DailyRationDao
import io.github.agimaulana.monity.model.DailyRation

@Database(
    version = 1,
    entities = [DailyRation::class]
)
@TypeConverters(TimestampConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract val dailyRationDao: DailyRationDao

    companion object {
        fun create(context: Context) = DatabaseBuilder.build<AppDatabase>(context, "monity")
    }
}