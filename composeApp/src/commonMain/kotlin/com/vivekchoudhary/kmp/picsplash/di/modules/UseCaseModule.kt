package com.vivekchoudhary.kmp.picsplash.di.modules

import com.vivekchoudhary.kmp.picsplash.domain.usecase.GetAllCollectionsUseCase
import com.vivekchoudhary.kmp.picsplash.domain.usecase.GetAllPhotosUseCase
import com.vivekchoudhary.kmp.picsplash.domain.usecase.GetSavedPhotosUseCase
import com.vivekchoudhary.kmp.picsplash.domain.usecase.GetTopicByIdUseCase
import com.vivekchoudhary.kmp.picsplash.domain.usecase.GetTopicsUseCase
import com.vivekchoudhary.kmp.picsplash.domain.usecase.GetUserProfileUseCase
import com.vivekchoudhary.kmp.picsplash.domain.usecase.SearchPhotosUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCasesModule: Module = module {
    factory { SearchPhotosUseCase(get()) }
    factory { GetAllPhotosUseCase(get()) }
    factory { GetTopicsUseCase(get()) }
    factory { GetAllCollectionsUseCase(get()) }
    factory { GetSavedPhotosUseCase(get()) }
    factory { GetUserProfileUseCase(get()) }
    factory { GetTopicByIdUseCase(get()) }
}
