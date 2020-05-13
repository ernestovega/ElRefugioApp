/*
    Copyright (C) 2020  Ernesto Vega de la Iglesia Soria
 
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/
package com.etologic.elrefugioapp.android.main.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.etologic.elrefugioapp.android.global.base.BaseViewModel
import com.etologic.elrefugioapp.core.model.enums.WebPageTypes
import com.etologic.elrefugioapp.core.use_cases.GetWebPagesUseCase
import io.reactivex.schedulers.Schedulers

class MainViewModel internal constructor(
    private val getWebPagesUseCase: GetWebPagesUseCase
) : BaseViewModel() {
    
    private val _loadAd = MutableLiveData<Boolean>()
    internal fun getLoadAd(): LiveData<Boolean> = _loadAd
    private val _interstitialAdFinished = MutableLiveData<Boolean>()
    internal fun getInterstitialFinished(): LiveData<Boolean> = _interstitialAdFinished
    
    internal fun getWebPage(webPageType: WebPageTypes): String =
        getWebPagesUseCase.getWebPage(webPageType)
            .subscribeOn(Schedulers.io())
            .blockingGet()
    
    internal fun loadIntersticial() {
        _loadAd.postValue(true)
    }
    
    internal fun setInterstitialAdFinished() {
        _interstitialAdFinished.postValue(true)
    }
}
