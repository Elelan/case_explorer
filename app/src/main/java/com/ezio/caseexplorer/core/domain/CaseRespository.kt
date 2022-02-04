package com.ezio.caseexplorer.core.domain

import com.ezio.caseexplorer.core.domain.models.CaseItem
import com.ezio.caseexplorer.core.domain.models.ScenarioItem
import com.ezio.caseexplorer.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CaseRepository {
    suspend fun getScenarios(): Flow<Resource<List<ScenarioItem>>>
    suspend fun getCase(id: Int): Flow<Resource<CaseItem>>
}