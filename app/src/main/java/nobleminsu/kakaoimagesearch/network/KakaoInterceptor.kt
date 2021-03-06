package nobleminsu.kakaoimagesearch.network

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import nobleminsu.kakaoimagesearch.R
import okhttp3.Interceptor
import javax.inject.Inject

class KakaoInterceptor @Inject constructor(
    @ApplicationContext context: Context
) {
    private val apiKey by lazy { "KakaoAK " + context.getString(R.string.key_kakao_rest_api) }
    val interceptor = Interceptor { chain ->
        chain.request().newBuilder()
            .apply {
                addHeader(
                    "Authorization", apiKey
                )
            }
            .let { chain.proceed(it.build()) }
    }
}