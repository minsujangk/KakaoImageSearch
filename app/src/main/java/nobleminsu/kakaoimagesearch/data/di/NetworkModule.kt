package nobleminsu.kakaoimagesearch.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nobleminsu.kakaoimagesearch.BuildConfig
import nobleminsu.kakaoimagesearch.network.interfaces.KakaoInterceptor
import nobleminsu.kakaoimagesearch.network.interfaces.MainApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(kakaoInterceptor: KakaoInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(kakaoInterceptor.interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesMainApiInterface(retrofit: Retrofit): MainApiInterface {
        return retrofit.create(MainApiInterface::class.java)
    }
}