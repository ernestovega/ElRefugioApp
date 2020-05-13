package com.etologic.elrefugioapp.data.remote.base

import io.reactivex.Single

abstract class BaseRemoteDataSource<T> {
    
    abstract fun get(): Single<T>
}