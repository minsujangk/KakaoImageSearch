package nobleminsu.kakaoimagesearch.data.sources.main.remote

import nobleminsu.kakaoimagesearch.network.interfaces.MainApiInterface
import javax.inject.Inject

class MainRemoteDataSourceImpl @Inject constructor(
    private val mainApiInterface: MainApiInterface
) : MainRemoteDataSource {
}