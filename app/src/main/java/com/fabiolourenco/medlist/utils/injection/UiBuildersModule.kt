package com.fabiolourenco.medlist.utils.injection

import com.fabiolourenco.medlist.ui.medlist.MedListActivity
import com.fabiolourenco.medlist.ui.newmed.AddMedDialogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributesMedListActivity(): MedListActivity

    @ContributesAndroidInjector
    abstract fun contributeAddMedDialogFragment(): AddMedDialogFragment
}