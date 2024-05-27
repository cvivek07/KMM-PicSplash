package com.vivekchoudhary.kmp.picsplash.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.AppDatabase
import platform.Foundation.NSHomeDirectory
import database.instantiateImpl

fun getAppDatabase(): AppDatabase {
    val dbFile = NSHomeDirectory() + "/kmm-pic-splash-app.db"
    return Room.databaseBuilder<AppDatabase>(
        name = dbFile,
        factory = { AppDatabase::class.instantiateImpl() }
    ).setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(true)
        .build()
}