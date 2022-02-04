package com.ezio.caseexplorer.core.domain.use_case

import com.ezio.caseexplorer.core.domain.CaseRepository
import com.ezio.caseexplorer.core.domain.models.ScenarioItem
import com.ezio.caseexplorer.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class FetchScenarioListUseCase(private val repository: CaseRepository) {
    suspend operator fun invoke() : Flow<Resource<List<ScenarioItem>>> {
        return repository.getScenarios()
    }
}