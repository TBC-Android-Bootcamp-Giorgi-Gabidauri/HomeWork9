package com.gabo.recyvlerviewhw.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gabo.recyvlerviewhw.database.dao.UserDao
import com.gabo.recyvlerviewhw.database.entity.User

@Database(entities = [User::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
}