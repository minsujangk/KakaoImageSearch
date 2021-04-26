package nobleminsu.kakaoimagesearch

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class KakaoImageSearchApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        appCtx = this.applicationContext
    }

    companion object {
        lateinit var appCtx: Context
    }
}