package com.ezio.caseexplorer.core.domain

interface CaseRespository {
    suspend fun getScenarios()
    suspend fun getCases(id: Int)
}