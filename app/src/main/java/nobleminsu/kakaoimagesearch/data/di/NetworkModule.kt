package nobleminsu.kakaoimagesearch.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nobleminsu.kakaoimagesearch.network.interfaces.MainApiInterface
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesMainApiInterface(retrofit: Retrofit): MainApiInterface {
        return retrofit.create(MainApiInterface::class.java)
    }
}