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
        return try {
            val result = api.getScenarios()
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun getCase(id: Int): Resource<CaseItem> {
        return try {
            val result = api.getCaseById(id)
            Resource.Success(result[0])
        } catch (e: Exception) {
            return Resource.Error(e);
        }
    }
}
