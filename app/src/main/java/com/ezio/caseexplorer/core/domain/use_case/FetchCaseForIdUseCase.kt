package com.ezio.caseexplorer.core.domain.use_case

import com.ezio.caseexplorer.core.domain.CaseRepository
import com.ezio.caseexplorer.core.domain.models.CaseItem
import com.ezio.caseexplorer.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class FetchCaseForIdUseCase(private val repository: CaseRepository) {
    suspend operator fun invoke(caseId: Int): Flow<Resource<CaseItem>> {
        return repository.getCase(caseId)
    }
}