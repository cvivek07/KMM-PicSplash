package database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import database.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photoentity")
    fun getAll(): Flow<List<PhotoEntity>>

    @Upsert
    suspend fun upsert(photo: PhotoEntity)

    @Delete
    suspend fun delete(photo: PhotoEntity)


}