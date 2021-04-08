package nobleminsu.kakaoimagesearch.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import nobleminsu.kakaoimagesearch.data.sources.MainDataSource
import nobleminsu.kakaoimagesearch.data.sources.MainDataSourceImpl
import nobleminsu.kakaoimagesearch.data.sources.remote.MainRemoteDataSource
import nobleminsu.kakaoimagesearch.data.sources.remote.MainRemoteDataSourceImpl
import nobleminsu.kakaoimagesearch.network.interfaces.MainApiInterface
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun providesMainRemoteDataSource(mainRemoteDataSourceImpl: MainRemoteDataSourceImpl): MainRemoteDataSource
}