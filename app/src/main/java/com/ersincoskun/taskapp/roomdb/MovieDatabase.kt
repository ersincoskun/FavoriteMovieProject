package com.ersincoskun.taskapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ersincoskun.taskapp.model.Movie

@Database(entities = [Movie::class], version = 4)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}