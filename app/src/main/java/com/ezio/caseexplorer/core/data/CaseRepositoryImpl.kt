package com.ezio.caseexplorer.core.data

import com.ezio.caseexplorer.core.data.remote.CaseApi
import com.ezio.caseexplorer.core.domain.CaseRepository
import com.ezio.caseexplorer.core.domain.models.CaseItem
import com.ezio.caseexplorer.core.domain.models.ScenarioItem
import com.ezio.caseexplorer.core.utils.Resource

class CaseRepositoryImpl(
    private val api: CaseApi
) : CaseRepository {
    override suspend fun getScenarios(): Resource<List<ScenarioItem>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCase(id: Int): Resource<CaseItem> {
        TODO("Not yet implemented")
    }
}
