package database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import database.entity.TopicEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TopicDao {

    @Query("SELECT * FROM topicentity")
    fun getAll(): Flow<List<TopicEntity>>

    @Upsert
    suspend fun upsert(topicEntity: TopicEntity)

    @Delete
    suspend fun delete(topicEntity: TopicEntity)


}