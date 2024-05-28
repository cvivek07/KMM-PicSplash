package com.vivekchoudhary.kmp.picsplash.presentation

import com.vivekchoudhary.kmp.picsplash.data.network.responses.Photo
import com.vivekchoudhary.kmp.picsplash.data.network.responses.Topic

/**
 * Temp solution for passing objects between screens
 * Currently, navigation library does not support this
 */
object NavigationUtil {

    lateinit var topic: Topic
    lateinit var photo: Photo

}