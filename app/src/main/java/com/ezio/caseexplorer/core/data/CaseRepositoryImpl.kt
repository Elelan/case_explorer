package com.ezio.caseexplorer.core.data

import com.ezio.caseexplorer.core.data.remote.CaseApi
import com.ezio.caseexplorer.core.domain.CaseRepository
import com.ezio.caseexplorer.core.domain.models.CaseItem
import com.ezio.caseexplorer.core.domain.models.ScenarioItem
import com.ezio.caseexplorer.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CaseRepositoryImpl(
    private val api: CaseApi
) : CaseRepository {
    override suspend fun getScenarios(): Flow<Resource<List<ScenarioItem>>> = flow{
        emit(Resource.Loading)
        try {
            val result = api.getScenarios()
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }

    override suspend fun getCase(id: Int): Flow<Resource<CaseItem>> = flow {
        emit(Resource.Loading)
        try {
            val result = api.getCaseById(id)
            emit(Resource.Success(result[0]))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }
    }
}
