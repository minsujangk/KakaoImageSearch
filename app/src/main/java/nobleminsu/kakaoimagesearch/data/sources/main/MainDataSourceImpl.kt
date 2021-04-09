package nobleminsu.kakaoimagesearch.data.sources.main

import nobleminsu.kakaoimagesearch.data.sources.main.remote.MainRemoteDataSource
import javax.inject.Inject

class MainDataSourceImpl @Inject constructor(
    private val mainRemoteDataSource: MainRemoteDataSource
) : MainDataSource {
}