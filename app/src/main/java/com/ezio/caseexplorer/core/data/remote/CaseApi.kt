package com.ezio.caseexplorer.core.data.remote

import com.ezio.caseexplorer.core.domain.models.Scenario
import retrofit2.http.GET

interface CaseApi {
    @GET("/scenarios/")
    suspend fun getScenarios(): List<Scenario>
}