package database

import com.vivekchoudhary.kmp.picsplash.data.network.responses.Collection
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Photo
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Topic
import database.entity.CollectionEntity
import database.entity.PhotoEntity
import database.entity.TopicEntity

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

fun List<Photo>.toPhotoEntityList(): List<PhotoEntity> {
    return this.map { it.toEntity() }
}

fun List<PhotoEntity>.toPhotoDomainList(): List<Photo> {
    return this.map { it.toDomain() }
}


fun Topic.toEntity(): TopicEntity {
    return TopicEntity(
        id = this.id,
        title = this.title,
        coverPhoto = this.coverPhoto,
        description = this.description
    )
}

fun TopicEntity.toDomain(): Topic {
    return Topic(
        id = this.id,
        title = this.title,
        coverPhoto = this.coverPhoto,
        description = this.description
    )
}

fun List<Topic>.toTopicEntityList(): List<TopicEntity> {
    return this.map { it.toEntity() }
}

fun List<TopicEntity>.toTopicDomainList(): List<Topic> {
    return this.map { it.toDomain() }
}

fun Collection.toEntity(): CollectionEntity {
    return CollectionEntity(
        id = this.id,
        title = this.title,
        coverPhoto = this.coverPhoto,
        description = this.description ?: ""
    )
}

fun CollectionEntity.toDomain(): Collection {
    return Collection(
        id = this.id,
        title = this.title,
        coverPhoto = this.coverPhoto,
        description = this.description
    )
}

fun List<Collection>.toCollectionEntityList(): List<CollectionEntity> {
    return this.map { it.toEntity() }
}

fun List<CollectionEntity>.toCollectionDomainList(): List<Collection> {
    return this.map { it.toDomain() }
}