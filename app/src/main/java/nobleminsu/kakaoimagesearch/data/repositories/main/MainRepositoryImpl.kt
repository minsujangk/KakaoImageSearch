package nobleminsu.kakaoimagesearch.data.repositories.main

import nobleminsu.kakaoimagesearch.data.sources.main.MainDataSource
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainDataSource: MainDataSource
) : MainRepository {
}