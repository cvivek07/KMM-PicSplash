package database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vivekchoudhary.kmp.picsplash.data.network.responses.CoverPhoto
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Urls
import com.vivekchoudhary.kmp.picsplash.data.network.responses.User
import kotlinx.serialization.SerialName

@Entity
data class CollectionEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    @Embedded
    val coverPhoto: CoverPhoto,
    val description: String
)