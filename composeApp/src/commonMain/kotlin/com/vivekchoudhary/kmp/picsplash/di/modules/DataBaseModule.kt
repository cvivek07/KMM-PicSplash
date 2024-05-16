package di.modules

import com.vivekchoudhary.kmp.picsplash.data.sqldelight.Database
import com.vivekchoudhary.kmp.picsplash.data.sqldelight.DatabaseDriverFactory
import org.koin.dsl.module

val sqlDelightModule = module {
    single { Database(get()) }
}

val dataBaseDriverFactory = module {
    single { DatabaseDriverFactory() }
}
