package nobleminsu.kakaoimagesearch.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nobleminsu.kakaoimagesearch.BuildConfig
import nobleminsu.kakaoimagesearch.network.KakaoInterceptor
import nobleminsu.kakaoimagesearch.network.interfaces.KakaoApiInterface
import nobleminsu.kakaoimagesearch.network.interfaces.MainApiInterface
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(kakaoInterceptor: KakaoInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(kakaoInterceptor.interceptor)
            .addInterceptor(HttpLoggingInterceptor { message ->
                kotlin.runCatching {
                    JsonParser().parse(message)
                }.onSuccess {
                    Timber.tag("OkHttp").d(message)
                }.onFailure {
                    Timber.tag("OkHttp").d(message)
                }
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
            .create()
    }

    @Provides
    @Singleton
    fun providesMainApiInterface(retrofit: Retrofit): MainApiInterface {
        return retrofit.create(MainApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun providesKakaoApiInterface(retrofit: Retrofit): KakaoApiInterface {
        return retrofit.create(KakaoApiInterface::class.java)
    }
}