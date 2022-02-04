package com.ezio.caseexplorer.core.domain.use_case

import com.ezio.caseexplorer.core.domain.CaseRespository
import com.ezio.caseexplorer.core.domain.models.ScenarioItem
import com.ezio.caseexplorer.core.utils.Resource

class FetchScenarioListUseCase(private val repository: CaseRespository) {
    suspend operator fun invoke() : Resource<List<ScenarioItem>> {
        return repository.getScenarios()
    }
}