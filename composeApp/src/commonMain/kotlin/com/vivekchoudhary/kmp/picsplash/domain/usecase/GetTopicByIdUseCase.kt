package com.vivekchoudhary.kmp.picsplash.domain.usecase

import com.vivekchoudhary.kmp.picsplash.data.repository.PhotosRepository

class GetTopicByIdUseCase(private val repository: PhotosRepository) {

    suspend operator fun invoke(id: String) = runCatching { repository.getTopic(id) }
}