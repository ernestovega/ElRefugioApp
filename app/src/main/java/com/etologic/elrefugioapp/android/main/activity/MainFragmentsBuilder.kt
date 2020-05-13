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

import com.etologic.elrefugioapp.android.main.fragments.adopt.AdoptFragment
import com.etologic.elrefugioapp.android.main.fragments.become_member.BecomeMemberFragment
import com.etologic.elrefugioapp.android.main.fragments.guau_walker.GuauWalkerFragment
import com.etologic.elrefugioapp.android.main.fragments.home.HomeFragment
import com.etologic.elrefugioapp.android.main.fragments.see_ads.SeeAdsFragment
import com.etologic.elrefugioapp.android.main.fragments.vet_services.VetServicesFragment
import com.etologic.elrefugioapp.android.main.fragments.welcoming.WelcomingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentsBuilder {
    
    @ContributesAndroidInjector
    internal abstract fun provideHomeFragmentFactory(): HomeFragment
    
    @ContributesAndroidInjector
    internal abstract fun provideAdoptFragmentFactory(): AdoptFragment
    
    @ContributesAndroidInjector
    internal abstract fun provideSeeAdsFragmentFactory(): SeeAdsFragment
    
    @ContributesAndroidInjector
    internal abstract fun provideBecomeMemberFragmentFactory(): BecomeMemberFragment
    
    @ContributesAndroidInjector
    internal abstract fun provideWelcomingFragmentFactory(): WelcomingFragment
    
    @ContributesAndroidInjector
    internal abstract fun provideGuauWalkerFragmentFactory(): GuauWalkerFragment
    
    @ContributesAndroidInjector
    internal abstract fun provideVetServicesFragmentFactory(): VetServicesFragment
}
