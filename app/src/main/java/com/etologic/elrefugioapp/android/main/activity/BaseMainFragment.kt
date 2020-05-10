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

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.etologic.elrefugioapp.android.global.base.BaseFragment
import javax.inject.Inject

abstract class BaseMainFragment : BaseFragment() {
    
    @Inject
    internal lateinit var viewModelFactory: MainViewModelFactory
    protected var activityViewModel: MainViewModel? = null
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { activityViewModel = ViewModelProvider(requireActivity(), viewModelFactory).get(MainViewModel::class.java) }
    }
}