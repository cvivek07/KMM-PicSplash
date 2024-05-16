package com.vivekchoudhary.kmp.picsplash.data.sqldelight

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory() {
    fun createDriver(): SqlDriver
}