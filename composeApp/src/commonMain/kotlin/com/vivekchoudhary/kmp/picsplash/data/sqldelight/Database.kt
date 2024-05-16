package com.vivekchoudhary.kmp.picsplash.data.sqldelight


class Database(private val databaseDriverFactory: DatabaseDriverFactory) {
    private var database: AppDatabase? = null

    private fun initDatabase() {
        if (database == null) {
            database = AppDatabase.invoke(databaseDriverFactory.createDriver())
        }
    }

    suspend operator fun <R> invoke(block: suspend (AppDatabase) -> R): R {
        initDatabase()
        return block(database!!)
    }
}