package com.vivekchoudhary.kmp.picsplash.di.modules

import com.vivekchoudhary.kmp.picsplash.database.getAppDatabase
import database.AppDatabase
import org.koin.dsl.module

actual val platformModule = module {
    single<AppDatabase> {
        getAppDatabase()
    }
}