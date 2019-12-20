package com.fabiolourenco.medlist.utils.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fabiolourenco.medlist.ui.MedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MedViewModel::class)
    abstract fun bindFavoriteShowsViewModel(medViewModel: MedViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: MedListViewModelFactory): ViewModelProvider.Factory
}