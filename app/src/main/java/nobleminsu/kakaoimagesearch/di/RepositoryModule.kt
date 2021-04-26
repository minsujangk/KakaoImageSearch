package nobleminsu.kakaoimagesearch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import nobleminsu.kakaoimagesearch.data.repositories.image_search.ImageSearchRepository
import nobleminsu.kakaoimagesearch.data.repositories.image_search.ImageSearchRepositoryImpl
import nobleminsu.kakaoimagesearch.data.repositories.main.MainRepository
import nobleminsu.kakaoimagesearch.data.repositories.main.MainRepositoryImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindsMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    @Binds
    abstract fun bindsImageSearchRepository(imageSearchRepositoryImpl: ImageSearchRepositoryImpl): ImageSearchRepository
}