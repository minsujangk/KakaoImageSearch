package nobleminsu.kakaoimagesearch.data.sources.remote

import nobleminsu.kakaoimagesearch.data.repositories.main.MainRepository
import nobleminsu.kakaoimagesearch.network.interfaces.MainApiInterface
import javax.inject.Inject

class MainRemoteDataSourceImpl @Inject constructor(
    private val mainApiInterface: MainApiInterface
) : MainRemoteDataSource {
}