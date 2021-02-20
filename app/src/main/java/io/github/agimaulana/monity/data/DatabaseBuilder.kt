package io.github.agimaulana.monity.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

class DatabaseBuilder {
    companion object {
        inline fun <reified T: RoomDatabase> build(
            context: Context,
            databaseName: String? = null
        ): T {
            val dbName = databaseName ?: T::class.java.simpleName
            val builder = Room.databaseBuilder(context, T::class.java, dbName)
            return builder.build()
        }
    }
}