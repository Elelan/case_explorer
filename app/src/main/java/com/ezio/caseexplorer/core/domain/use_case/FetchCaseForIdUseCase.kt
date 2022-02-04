package com.ezio.caseexplorer.core.domain.use_case

import com.ezio.caseexplorer.core.domain.CaseRespository
import com.ezio.caseexplorer.core.domain.models.CaseItem
import com.ezio.caseexplorer.core.utils.Resource

class FetchCaseForIdUseCase(private val repository: CaseRespository) {
    suspend operator fun invoke(caseId: Int): Resource<CaseItem> {
        return repository.getCase(caseId)
    }
}