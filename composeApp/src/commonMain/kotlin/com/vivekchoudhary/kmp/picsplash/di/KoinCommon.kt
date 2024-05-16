package com.vivekchoudhary.kmp.picsplash.di

import com.vivekchoudhary.kmp.picsplash.di.modules.networkModule
import com.vivekchoudhary.kmp.picsplash.di.modules.useCasesModule
import di.modules.dataBaseDriverFactory
import di.modules.repositoryModule
import di.modules.sqlDelightModule
import di.modules.viewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration


fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            viewModelModule,
            useCasesModule,
            repositoryModule,
            sqlDelightModule,
            networkModule,
            dataBaseDriverFactory
        )
    }


//using in iOS
fun initKoin() = initKoin {}