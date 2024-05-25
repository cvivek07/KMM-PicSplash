package com.vivekchoudhary.kmp.picsplash.data.local

import database.PhotoEntity

interface LocalDataSource {

    suspend fun insertPhoto(photo: PhotoEntity)

    suspend fun getAllPhotos(): List<PhotoEntity>

    suspend fun deletePhoto(photo: PhotoEntity)
}