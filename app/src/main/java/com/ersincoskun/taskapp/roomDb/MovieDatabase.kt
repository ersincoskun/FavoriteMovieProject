package com.ersincoskun.taskapp.roomDb

import androidx.room.Database
import com.ersincoskun.taskapp.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase {
    abstract fun movieDao(): MovieDao
}