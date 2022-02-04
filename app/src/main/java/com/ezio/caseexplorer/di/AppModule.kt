package com.ezio.caseexplorer.di

import com.ezio.caseexplorer.config.AppConfig
import com.ezio.caseexplorer.core.data.CaseRepositoryImpl
import com.ezio.caseexplorer.core.data.remote.CaseApi
import com.ezio.caseexplorer.core.domain.CaseRepository
import com.ezio.caseexplorer.core.domain.use_case.AppUseCases
import com.ezio.caseexplorer.core.domain.use_case.FetchCaseForIdUseCase
import com.ezio.caseexplorer.core.domain.use_case.FetchScenarioListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return okhttp3.OkHttpClient.Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideCaseApi(
        client: OkHttpClient,
    ): CaseApi {
        return Retrofit.Builder()
            .baseUrl(AppConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(CaseApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCaseRepository(
        api: CaseApi
    ): CaseRepository {
        return CaseRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideAppUseCases(repository: CaseRepository): AppUseCases {
        return AppUseCases(
            fetchScenarioListUseCase = FetchScenarioListUseCase(repository),
            fetchCaseForIdUseCase = FetchCaseForIdUseCase(repository)
        )
    }
}