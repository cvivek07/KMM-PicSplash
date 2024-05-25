package database

import com.vivekchoudhary.kmp.picsplash.data.network.responses.Photo

fun Photo.toEntity(): PhotoEntity {
    return PhotoEntity(
        id = this.id,
        urls = this.urls,
        width = this.width,
        height = this.height,
        altDescription = this.altDescription,
        description = this.description,
        likesCount = this.likesCount,
        user = this.user
    )
}

fun PhotoEntity.toDomain(): Photo {
    return Photo(
        id = this.id,
        urls = this.urls,
        width = this.width,
        height = this.height,
        altDescription = this.altDescription,
        description = this.description,
        likesCount = this.likesCount,
        user = this.user
    )
}

fun List<Photo>.toEntityList(): List<PhotoEntity> {
    return this.map { it.toEntity() }
}

fun List<PhotoEntity>.toDomainList(): List<Photo> {
    return this.map { it.toDomain() }
}
