package database

import androidx.room.TypeConverter
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Links
import com.vivekchoudhary.kmp.picsplash.data.network.responses.ProfileImage

import com.vivekchoudhary.kmp.picsplash.data.network.responses.User
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun fromProfileImage(profileImage: ProfileImage): String {
        return Json.encodeToString(profileImage)
    }

    @TypeConverter
    fun toProfileImage(profileImageString: String): ProfileImage {
        return Json.decodeFromString(profileImageString)
    }

    @TypeConverter
    fun fromUser(user: User): String {
        return Json.encodeToString(user)
    }

    @TypeConverter
    fun toUser(userString: String): User {
        return Json.decodeFromString(userString)
    }


    @TypeConverter
    fun fromLinks(links: Links): String {
        return Json.encodeToString(links)
    }

    @TypeConverter
    fun toLinks(linksString: String): Links {
        return Json.decodeFromString(linksString)
    }
}
