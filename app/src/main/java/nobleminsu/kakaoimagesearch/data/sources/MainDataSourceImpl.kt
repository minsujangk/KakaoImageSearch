package nobleminsu.kakaoimagesearch.data.sources

import nobleminsu.kakaoimagesearch.data.sources.remote.MainRemoteDataSource
import javax.inject.Inject

class MainDataSourceImpl @Inject constructor(
    private val mainRemoteDataSource: MainRemoteDataSource
) : MainDataSource {
}