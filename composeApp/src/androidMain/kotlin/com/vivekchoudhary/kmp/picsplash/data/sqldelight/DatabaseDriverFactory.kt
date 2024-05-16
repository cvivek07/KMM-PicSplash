package com.vivekchoudhary.kmp.picsplash.data.sqldelight

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.vivekchoudhary.kmp.picsplash.MyApplication

actual class DatabaseDriverFactory {

    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = AppDatabase.Schema,
            context = MyApplication.instance,
            name = "MyAppSQLDelightDatabase.db"
        )
    }
}