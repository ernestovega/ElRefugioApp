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

class MainViewModel internal constructor() : BaseViewModel() {
    
    internal fun getInterstitialAdUnit(): String = "ca-app-pub-3940256099942544/1033173712"
    internal fun getBannerAdUnit(): String = "ca-app-pub-2237700199215764/3601305954"
    
    private val _loadAd = MutableLiveData<Boolean>()
    internal fun getLoadAd(): LiveData<Boolean> = _loadAd
    private val _interstitialAdFinished = MutableLiveData<Boolean>()
    internal fun getInterstitialAdFinished(): LiveData<Boolean> = _interstitialAdFinished
    private val _interstitialAdFailedToLoad = MutableLiveData<Boolean>()
    internal fun getInterstitialAdFailedToLoad(): LiveData<Boolean> = _interstitialAdFailedToLoad
    private val _interstitialAdLoaded = MutableLiveData<Boolean>()
    internal fun getInterstitialAdLoaded(): LiveData<Boolean> = _interstitialAdLoaded
    
    internal fun loadInterstitial() {
        _loadAd.postValue(true)
    }
    
    internal fun setInterstitialAdClosed() {
        _interstitialAdFinished.postValue(true)
    }
    
    internal fun setInterstitialAdLoaded() {
        _interstitialAdLoaded.postValue(true)
    }
    
    internal fun setInterstitialAdFailedToLoad() {
        _interstitialAdFailedToLoad.postValue(true)
    }
}
