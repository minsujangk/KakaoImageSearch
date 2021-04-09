package nobleminsu.kakaoimagesearch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import nobleminsu.kakaoimagesearch.data.sources.main.MainDataSource
import nobleminsu.kakaoimagesearch.data.sources.main.MainDataSourceImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataSourceModule {
    @Binds
    abstract fun bindsMainDataSource(mainDataSourceImpl: MainDataSourceImpl): MainDataSource
}