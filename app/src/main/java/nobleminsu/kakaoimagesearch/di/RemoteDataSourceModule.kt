package nobleminsu.kakaoimagesearch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import nobleminsu.kakaoimagesearch.data.sources.main.remote.MainRemoteDataSource
import nobleminsu.kakaoimagesearch.data.sources.main.remote.MainRemoteDataSourceImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun providesMainRemoteDataSource(mainRemoteDataSourceImpl: MainRemoteDataSourceImpl): MainRemoteDataSource
}