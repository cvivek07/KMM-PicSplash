package di.modules

import com.vivekchoudhary.kmp.picsplash.data.network.ApiService
import com.vivekchoudhary.kmp.picsplash.data.network.ApiServiceImpl
import com.vivekchoudhary.kmp.picsplash.data.repository.ICacheData
import com.vivekchoudhary.kmp.picsplash.data.repository.PhotoRepositoryImpl
import com.vivekchoudhary.kmp.picsplash.data.repository.PhotosRepository
import com.vivekchoudhary.kmp.picsplash.data.sqldelight.CacheDataImp
import org.koin.dsl.module


val repositoryModule = module {
    single<PhotosRepository> { PhotoRepositoryImpl(get(),get()) }
    single<ApiService> { ApiServiceImpl(get()) }
    single<ICacheData> { CacheDataImp(get()) }
}