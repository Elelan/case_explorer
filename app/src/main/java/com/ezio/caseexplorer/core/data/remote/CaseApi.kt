package com.ezio.caseexplorer.core.data.remote

import com.ezio.caseexplorer.core.domain.models.Case
import com.ezio.caseexplorer.core.domain.models.Scenario
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CaseApi {
    @GET("/scenarios")
    suspend fun getScenarios(): Scenario

    @GET("/scenarios/cases/{caseId}")
    suspend fun getCaseById(@Path("caseId")id: Int): Case
}