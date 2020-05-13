package com.etologic.elrefugioapp.data.remote.base

import io.reactivex.Single
import javax.inject.Singleton

@Singleton
abstract class BaseMemoryDataSource<T> {
    
    protected var element: T? = null
    
    protected open fun has(): Single<Boolean> = Single.just(element != null)
    
    internal open fun get(): Single<T> = synchronized(this) { //ToDo: Esto es mejor que la opcion del semáforo?
        has()
            .map {
                if (it)
                    element
                else
                    throw Exception("${this.javaClass.name} - get()")
            }
    }
    
    internal open fun save(element: T): Single<T> {
        this.element = element
        return Single.just(element)
    }
    
    internal open fun invalidate() {
        element = null
    }
}