package com.vivekchoudhary.kmp.picsplash.di

import com.vivekchoudhary.kmp.picsplash.di.modules.networkModule
import com.vivekchoudhary.kmp.picsplash.di.modules.platformModule
import com.vivekchoudhary.kmp.picsplash.di.modules.useCasesModule
import com.vivekchoudhary.kmp.picsplash.di.modules.repositoryModule
import di.modules.dispatcherModule
import com.vivekchoudhary.kmp.picsplash.di.modules.viewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration


fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            viewModelModule,
            dispatcherModule,
            useCasesModule,
            repositoryModule,
            networkModule,
            platformModule
        )
    }


//using in iOS
fun initKoin() = initKoin {}