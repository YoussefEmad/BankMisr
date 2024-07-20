package com.banqurmisr.chanllenge5.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.banqurmisr.chanllenge5.data.models.LocalMovieModel
import com.banqurmisr.chanllenge5.data.models.MovieKeys

@Database(entities = [LocalMovieModel::class , MovieKeys::class], version = 1  , exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}