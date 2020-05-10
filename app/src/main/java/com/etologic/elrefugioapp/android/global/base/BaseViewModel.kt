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
package com.etologic.elrefugioapp.android.global.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.etologic.elrefugioapp.BuildConfig
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    
    protected var disposables = CompositeDisposable()
    private var _error = MutableLiveData<Throwable>()
    internal fun getError(): LiveData<Throwable> = _error
    
    private var _alertDialogMessage = MutableLiveData<String>()
    internal fun getAlertDialogMessage(): LiveData<String> = _alertDialogMessage
    
    private var _snackbarMessage = MutableLiveData<String>()
    internal fun getSnackbarMessage(): LiveData<String> = _snackbarMessage
    
    internal fun showError(throwable: Throwable) {
        _alertDialogMessage.postValue(throwable.message)
        if (BuildConfig.DEBUG)
            throwable.printStackTrace()
    }
    
    override fun onCleared() {
        if (!disposables.isDisposed)
            disposables.dispose()
        super.onCleared()
    }
}
