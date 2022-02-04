package com.ezio.caseexplorer.ui.feature_scenarios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezio.caseexplorer.core.domain.use_case.AppUseCases
import com.ezio.caseexplorer.core.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
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
            val result = appUseCases.fetchScenarioListUseCase()
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
            }
        }
    }
}