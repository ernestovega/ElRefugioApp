package com.etologic.elrefugioapp.data.repositories.web_pages

import com.etologic.elrefugioapp.core.model.enums.WebPageTypes
import io.reactivex.Single
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WebPagesMemoryDataSource
@Inject internal constructor() {
    
    private var hashMapPages: EnumMap<WebPageTypes, String> = EnumMap(WebPageTypes::class.java)
    
    internal fun get(webPageTypes: WebPageTypes): Single<String> =
        if (hashMapPages.containsKey(webPageTypes))
            Single.just(hashMapPages[webPageTypes])
        else
            Single.error(Exception("EL_REFUGIO_LOG -> WebPagesMemoryDataSource: $webPageTypes not found"))
    
    internal fun save(key: WebPageTypes, value: String) {
        this.hashMapPages[key] = value
    }
}