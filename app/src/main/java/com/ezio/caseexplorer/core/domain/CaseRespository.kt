package com.ezio.caseexplorer.core.domain

import com.ezio.caseexplorer.core.domain.models.CaseItem
import com.ezio.caseexplorer.core.domain.models.ScenarioItem
import com.ezio.caseexplorer.core.utils.Resource

interface CaseRepository {
    suspend fun getScenarios(): Resource<List<ScenarioItem>>
    suspend fun getCase(id: Int): Resource<CaseItem>
}