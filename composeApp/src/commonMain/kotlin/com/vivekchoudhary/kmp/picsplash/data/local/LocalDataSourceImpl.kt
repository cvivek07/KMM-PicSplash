package com.vivekchoudhary.kmp.picsplash.data.local

import database.AppDatabase
import database.entity.CollectionEntity
import database.entity.PhotoEntity
import database.entity.TopicEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(
    private val db: AppDatabase,
    private val dispatcher: CoroutineDispatcher,
) : LocalDataSource {

    override suspend fun upsertPhoto(photo: PhotoEntity) {
        withContext(dispatcher) {
            db.photoDao().upsert(photo)
        }
    }

    override suspend fun getAllPhotos(): List<PhotoEntity> {
       return withContext(dispatcher) {
            db.photoDao().getAll().first()
        }
    }

    override suspend fun deletePhoto(photo: PhotoEntity) {
        withContext(dispatcher) {
            db.photoDao().delete(photo)
        }
    }

    override suspend fun upsertTopic(topicEntity: TopicEntity) {
        withContext(dispatcher) {
            db.topicDao().upsert(topicEntity)
        }
    }

    override suspend fun getAllTopics(): List<TopicEntity> {
        return withContext(dispatcher) {
            db.topicDao().getAll().first()
        }
    }

    override suspend fun deleteTopic(topicEntity: TopicEntity) {
        withContext(dispatcher) {
            db.topicDao().delete(topicEntity)
        }
    }

    override suspend fun upsertCollection(collectionEntity: CollectionEntity) {
        withContext(dispatcher) {
            db.collectionDao().upsert(collectionEntity)
        }
    }

    override suspend fun getAllCollections(): List<CollectionEntity> {
        return withContext(dispatcher) {
            db.collectionDao().getAll().first()
        }
    }

    override suspend fun deleteCollection(collectionEntity: CollectionEntity) {
        withContext(dispatcher) {
            db.collectionDao().delete(collectionEntity)
        }
    }

}