package com.fabiolourenco.medlist.utils.injection

import androidx.room.Room
import com.fabiolourenco.medlist.MedApp
import com.fabiolourenco.medlist.data.AppRepository
import com.fabiolourenco.medlist.data.Repository
import com.fabiolourenco.medlist.data.api.ApiHelper
import com.fabiolourenco.medlist.data.api.AppApiHelper
import com.fabiolourenco.medlist.data.api.FakeInterceptor
import com.fabiolourenco.medlist.data.api.ImageApi
import com.fabiolourenco.medlist.data.db.MedDatabase
import com.fabiolourenco.medlist.data.db.dao.MedDao
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesDatabase(medApp: MedApp): MedDatabase =
        Room.databaseBuilder(medApp, MedDatabase::class.java, "Medication.db").build()

    @Singleton
    @Provides
    fun providesMedDao(database: MedDatabase): MedDao = database.medDao

    @Singleton
    @Provides
    fun providesRepository(medDao: MedDao, executor: ExecutorService, apiHelper: ApiHelper): Repository
            = AppRepository(medDao, executor, apiHelper)

    @Singleton
    @Provides
    fun providesExecutor(): ExecutorService = Executors.newSingleThreadExecutor()

    @Provides
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(FakeInterceptor())
            .build()

    // For this project we use the FakeInterceptor to hardcoded an api request response
    @Singleton
    @Provides
    fun providesImageApi(): ImageApi =
        Retrofit.Builder()
            .baseUrl("https://google.pt") // Anything really, this will be a fake request
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder()
                .addInterceptor(FakeInterceptor())
                .build())
            .build()
            .create(ImageApi::class.java)

    @Singleton
    @Provides
    fun providesApiHelper(imageApi: ImageApi): ApiHelper = AppApiHelper(imageApi)
}