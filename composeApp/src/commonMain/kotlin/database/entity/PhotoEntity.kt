package database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Urls
import com.vivekchoudhary.kmp.picsplash.data.network.responses.User

@Entity
data class PhotoEntity(
    @PrimaryKey
    val id: String,
    @Embedded
    val urls: Urls,
    val width: Int,
    val height: Int,
    val altDescription: String? = "",
    val description: String? = "",
    val likesCount: Int,
    @Embedded
    val user: User
)