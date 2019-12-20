package com.fabiolourenco.medlist.utils.injection

import com.fabiolourenco.medlist.MedApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        UiBuildersModule::class,
        ViewModelModule::class
    ]
)

interface InjectionComponent : AndroidInjector<MedApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: MedApp): Builder

        fun build(): InjectionComponent
    }
}