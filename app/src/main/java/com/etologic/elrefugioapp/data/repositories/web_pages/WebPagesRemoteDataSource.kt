package com.etologic.elrefugioapp.data.repositories.web_pages

import com.etologic.elrefugioapp.core.model.enums.WebPageTypes
import com.etologic.elrefugioapp.core.model.enums.WebPageTypes.*
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

internal class WebPagesRemoteDataSource
@Inject constructor() {
    
    companion object {
        private const val TAG_HEADER_ORIGINAL: String = "<header class=\"header\""
        private const val TAG_HEADER_NEW: String = "<header style=\"visibility: hidden;\" class=\"header\""
        private const val TAG_ASIDE_ORIGINAL: String = "<aside class=\"sidebar\""
        private const val TAG_ASIDE_NEW: String = "<aside style=\"visibility: hidden;\" class=\"sidebar\""
    }
    
    private val httpClient: OkHttpClient = OkHttpClient()
    
    internal fun get(webPageTypes: WebPageTypes): Single<String> =
        when (webPageTypes) {
            HOME -> run("https://elrefugio.org/home")
            ADOPT -> run("https://elrefugio.org/adopta")
            BECOME_MEMBER -> run("https://elrefugio.org/hazte-socio")
            WELCOMING -> run("https://elrefugio.org/nodriza")
            GUAU_WALKER -> run("https://elrefugio.org/guau-walker")
            VET_SERVICES -> run("https://elrefugio.org/ayudanos/centro-veterinario")
        }
    
    private fun run(url: String): Single<String> =
        Single.fromCallable {
            val request = Request.Builder().url(url).build()
            val response = httpClient.newCall(request).execute()
            if (response.isSuccessful && response.body != null)
                response.body!!.string()
            else
                throw Exception("EL_REFUGIO_LOG -> WebPagesRemoteDataSource: $url load failed")
        }
}
