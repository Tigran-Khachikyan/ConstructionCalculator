package com.txsoft.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.txsoft.data.entities.ModelEntity

@androidx.room.Database(
    entities = [ModelEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Database : RoomDatabase() {

    abstract fun getModelDao(): ModelDao

    companion object {
        @Volatile
        private var instance: Database? = null

        operator fun invoke(context: Context): Database {
            return instance ?: synchronized(this) {
                val newInstance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "CONSTRUCTOR_CALCULATOR_DB"
                ).build()
                instance = newInstance
                return newInstance
            }
        }
    }
}