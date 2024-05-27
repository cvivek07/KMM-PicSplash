package database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import database.dao.CollectionDao
import database.dao.PhotoDao
import database.dao.TopicDao
import database.entity.CollectionEntity
import database.entity.PhotoEntity
import database.entity.TopicEntity

@Database(entities = [PhotoEntity::class, TopicEntity::class, CollectionEntity::class], version = 4)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() ,DB {

    abstract fun photoDao(): PhotoDao

    abstract fun topicDao(): TopicDao

    abstract fun collectionDao(): CollectionDao

    override fun clearAllTables() {
        super.clearAllTables()
    }

}

// FIXME: Added a hack to resolve below issue:
// Class 'AppDatabase_Impl' is not abstract and does not implement abstract base class member 'clearAllTables'.
interface DB {
    fun clearAllTables() {}
}