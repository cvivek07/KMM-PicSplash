package com.vivekchoudhary.kmp.picsplash.domain.usecase

import com.vivekchoudhary.kmp.picsplash.data.repository.PhotosRepository

class GetAllCollectionsUseCase(private val repository: PhotosRepository) {

    suspend operator fun invoke() = runCatching { repository.getCollections() }
}