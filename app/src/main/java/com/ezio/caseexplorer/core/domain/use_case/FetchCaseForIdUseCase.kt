package com.ezio.caseexplorer.core.domain.use_case

import com.ezio.caseexplorer.core.domain.CaseRepository
import com.ezio.caseexplorer.core.domain.models.CaseItem
import com.ezio.caseexplorer.core.utils.Resource

class FetchCaseForIdUseCase(private val repository: CaseRepository) {
    suspend operator fun invoke(caseId: Int): Resource<CaseItem> {
        return repository.getCase(caseId)
    }
}