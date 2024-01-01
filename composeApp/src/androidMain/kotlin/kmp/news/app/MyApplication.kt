package kmp.news.app

import android.app.Application
import android.content.Context
import momin.tahir.kmp.newsapp.domain.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger


class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        initKoin {
            androidLogger()
            androidContext(this@MyApplication)
        }
    }


    companion object {
        lateinit var instance: MyApplication
            private set
    }
}