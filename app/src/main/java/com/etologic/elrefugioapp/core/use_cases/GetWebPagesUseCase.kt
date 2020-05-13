package com.etologic.elrefugioapp.core.use_cases

import com.etologic.elrefugioapp.core.model.enums.WebPageTypes
import com.etologic.elrefugioapp.data.repositories.web_pages.WebPagesRepository
import io.reactivex.Single
import javax.inject.Inject

class GetWebPagesUseCase
@Inject internal constructor(private val webPagesRepository: WebPagesRepository) {
    
    internal fun getWebPage(webPageType: WebPageTypes): Single<String> =
        webPagesRepository.get(webPageType)
}
