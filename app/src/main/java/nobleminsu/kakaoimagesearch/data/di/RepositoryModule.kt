package nobleminsu.kakaoimagesearch.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import nobleminsu.kakaoimagesearch.data.repositories.main.MainRepository
import nobleminsu.kakaoimagesearch.data.repositories.main.MainRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}