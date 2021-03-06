package com.ezio.caseexplorer.ui.feature_scenarios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezio.caseexplorer.core.domain.models.ScenarioItem
import com.ezio.caseexplorer.core.domain.use_case.AppUseCases
import com.ezio.caseexplorer.core.utils.Resource
import com.ezio.caseexplorer.core.utils.exhaustive
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScenarioViewModel @Inject constructor(
    private val appUseCases: AppUseCases
): ViewModel() {

    private val _state = Channel<UiStateEvents>()
    val state = _state.receiveAsFlow()

    init {
        fetchScenarioList()
    }

    private fun fetchScenarioList() {
        viewModelScope.launch {
            appUseCases.fetchScenarioListUseCase().collect { result ->
                when(result) {
                    is Resource.Loading -> {
                        _state.send(UiStateEvents.Loading(true))
                    }
                    is Resource.Error -> {
                        _state.send(UiStateEvents.Loading(false))
                    }
                    is Resource.Success -> {
                        val data = result.data
                        _state.send(UiStateEvents.Loading(false))
                        _state.send(UiStateEvents.LoadScenarioList(data))
                    }
                }.exhaustive
            }

        }
    }

    fun navigateToCaseDetail(item: ScenarioItem) {
        viewModelScope.launch {
            _state.send(UiStateEvents.NavigateToCase(item.caseId))
        }
    }
}