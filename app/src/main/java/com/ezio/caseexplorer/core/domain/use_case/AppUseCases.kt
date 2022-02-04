package com.ezio.caseexplorer.core.domain.use_case

data class AppUseCases(
    val fetchScenarioListUseCase: FetchScenarioListUseCase,
    val fetchCaseForIdUseCase: FetchCaseForIdUseCase
)
