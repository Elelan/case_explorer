package com.ezio.caseexplorer.di

import com.ezio.caseexplorer.core.data.remote.CaseApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return okhttp3.OkHttpClient.Builder()
            .addInterceptor {
                it.proceed(it.request())
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideCaseApi(client: OkHttpClient): CaseApi {
        return Retrofit.Builder()
            .addConverterFactory()
    }
}