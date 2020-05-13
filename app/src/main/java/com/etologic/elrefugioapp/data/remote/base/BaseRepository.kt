package com.etologic.elrefugioapp.data.remote.base

import io.reactivex.Single

abstract class BaseRepository<T>(
    private val memoryDataSource: BaseMemoryDataSource<T>,
    private val remoteDataSource: BaseRemoteDataSource<T>
) {
    
    /**
     * Get the element from memory. If fails, tries from remote and saves it locally when succed.
     */
    open fun get(): Single<T> =
        memoryDataSource.get()
            .onErrorResumeNext { getFromRemote() }
    
    /**
     * Refresh the element in memory from remote and returns it.
     */
    open fun getFromRemote() =
        remoteDataSource.get()
            .flatMap { memoryDataSource.save(it) }
    
    /**
     * Removes the memory element
     */
    open fun invalidateData() =
        memoryDataSource.invalidate()
}