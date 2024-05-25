package com.vivekchoudhary.kmp.picsplash.di.modules

import com.vivekchoudhary.kmp.picsplash.data.local.LocalDataSource
import com.vivekchoudhary.kmp.picsplash.data.local.LocalDataSourceImpl
import com.vivekchoudhary.kmp.picsplash.data.network.ApiService
import com.vivekchoudhary.kmp.picsplash.data.network.ApiServiceImpl
import com.vivekchoudhary.kmp.picsplash.data.repository.PhotoRepositoryImpl
import com.vivekchoudhary.kmp.picsplash.data.repository.PhotosRepository
import di.modules.Dispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module


val repositoryModule = module {
    single<PhotosRepository> { PhotoRepositoryImpl(get(), get()) }
    single<ApiService> { ApiServiceImpl(get()) }
    single<LocalDataSource> { LocalDataSourceImpl(get(), get(named(Dispatcher.IO))) }
}