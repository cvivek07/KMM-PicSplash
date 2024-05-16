package di.modules

import com.vivekchoudhary.kmp.picsplash.presentation.screens.detail.DetailScreenViewModel
import com.vivekchoudhary.kmp.picsplash.presentation.screens.home.HomeScreenViewModel
import com.vivekchoudhary.kmp.picsplash.presentation.screens.profile.ProfileViewModel
import com.vivekchoudhary.kmp.picsplash.presentation.screens.saved_photos.SavedPhotosScreenViewModel
import com.vivekchoudhary.kmp.picsplash.presentation.screens.search_photos.SearchPhotosScreenViewModel
import com.vivekchoudhary.kmp.picsplash.presentation.screens.search_photos.TopicScreenViewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { HomeScreenViewModel(get()) }
    factory { SearchPhotosScreenViewModel(get(), get(), get()) }
    factory { ProfileViewModel(get()) }
    factory { DetailScreenViewModel() }
    factory { SavedPhotosScreenViewModel(get()) }
    factory { TopicScreenViewModel(get()) }
}
