package com.vivekchoudhary.kmp.picsplash.domain.usecase

import com.vivekchoudhary.kmp.picsplash.data.repository.PhotosRepository

class GetAllPhotosUseCase(private val repository: PhotosRepository) {

    suspend operator fun invoke(refresh: Boolean) = runCatching { repository.getPhotos(refresh) }
}