package com.etologic.elrefugioapp.data.repositories.web_pages

import com.etologic.elrefugioapp.core.model.enums.WebPageTypes
import io.reactivex.Single
import javax.inject.Inject

class WebPagesRepository
@Inject internal constructor(
    private val remoteDataSource: WebPagesRemoteDataSource,
    private val memoryDataSource: WebPagesMemoryDataSource
) {
    
    internal fun get(webPageTypes: WebPageTypes): Single<String> =
        memoryDataSource.get(webPageTypes)
            .onErrorResumeNext {
                remoteDataSource.get(webPageTypes)
                    .doOnSuccess { memoryDataSource.save(webPageTypes, it) }
            }
}
