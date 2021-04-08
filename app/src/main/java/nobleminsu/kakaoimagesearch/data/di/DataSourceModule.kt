package nobleminsu.kakaoimagesearch.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import nobleminsu.kakaoimagesearch.data.sources.MainDataSource
import nobleminsu.kakaoimagesearch.data.sources.MainDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindMainDataSource(mainDataSourceImpl: MainDataSourceImpl): MainDataSource
}