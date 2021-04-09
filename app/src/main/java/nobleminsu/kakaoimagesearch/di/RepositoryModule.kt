package nobleminsu.kakaoimagesearch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import nobleminsu.kakaoimagesearch.data.repositories.main.MainRepository
import nobleminsu.kakaoimagesearch.data.repositories.main.MainRepositoryImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun providesMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository
}