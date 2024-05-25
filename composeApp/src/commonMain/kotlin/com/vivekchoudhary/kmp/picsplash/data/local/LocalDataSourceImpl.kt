package com.vivekchoudhary.kmp.picsplash.data.local

import database.AppDatabase
import database.PhotoEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class LocalDataSourceImpl(
    private val db: AppDatabase,
    private val dispatcher: CoroutineDispatcher,
) : LocalDataSource {

    override suspend fun insertPhoto(photo: PhotoEntity) {
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

}