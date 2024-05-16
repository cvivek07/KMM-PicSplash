package com.vivekchoudhary.kmp.picsplash.domain.usecase

import com.vivekchoudhary.kmp.picsplash.data.repository.PhotosRepository

class SearchPhotosUseCase(private val repository: PhotosRepository) {

    suspend operator fun invoke(query: String) = repository.searchPhotos(query).results
}