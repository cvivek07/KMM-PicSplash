package com.vivekchoudhary.kmp.picsplash.data.local

import database.entity.CollectionEntity
import database.entity.PhotoEntity
import database.entity.TopicEntity

interface LocalDataSource {

    suspend fun upsertPhoto(photo: PhotoEntity)

    suspend fun getAllPhotos(): List<PhotoEntity>

    suspend fun deletePhoto(photo: PhotoEntity)

    suspend fun upsertTopic(topicEntity: TopicEntity)

    suspend fun getAllTopics(): List<TopicEntity>

    suspend fun deleteTopic(topicEntity: TopicEntity)

    suspend fun upsertCollection(collectionEntity: CollectionEntity)

    suspend fun getAllCollections(): List<CollectionEntity>

    suspend fun deleteCollection(collectionEntity: CollectionEntity)

}