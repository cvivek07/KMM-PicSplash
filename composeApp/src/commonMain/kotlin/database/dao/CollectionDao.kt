package database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import database.entity.CollectionEntity
import database.entity.TopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CollectionDao {

    @Query("SELECT * FROM collectionentity")
    fun getAll(): Flow<List<CollectionEntity>>

    @Upsert
    suspend fun upsert(collectionEntity: CollectionEntity)

    @Delete
    suspend fun delete(collectionEntity: CollectionEntity)


}