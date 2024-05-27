package com.vivekchoudhary.kmp.picsplash.database

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import database.AppDatabase

fun getAppDatabase(context: Context): AppDatabase {
    val dbFile = context.getDatabasePath("kmm-pic-splash-app.db")
    return Room.databaseBuilder<AppDatabase>(
        context = context.applicationContext, name = dbFile.absolutePath
    ).setDriver(BundledSQLiteDriver()).fallbackToDestructiveMigration(true).build()
}