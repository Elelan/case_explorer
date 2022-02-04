package com.ezio.caseexplorer.ui.feature_cases

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezio.caseexplorer.core.domain.models.CaseItem
import com.ezio.caseexplorer.core.domain.use_case.AppUseCases
import com.ezio.caseexplorer.core.utils.Resource
import com.ezio.caseexplorer.core.utils.exhaustive
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CaseViewModel @Inject constructor(
    private val appUseCases: AppUseCases,
    sate: SavedStateHandle
    ): ViewModel(){

    private val _event = Channel<UiEvents>()
    val event = _event.receiveAsFlow()

    val caseId = sate.get<Int>("caseId")

    init {
        loadCaseDetails(caseId)
    }

    private fun loadCaseDetails(caseId: Int?) {
        viewModelScope.launch {
            caseId?.let {
                val result = appUseCases.fetchCaseForIdUseCase(caseId)
                when(result) {
                    is Resource.Error -> {
                        // error
                        _event.send(UiEvents.Loading(true))
                    }
                    Resource.Loading -> {
                        _event.send(UiEvents.Loading(true))
                    }
                    is Resource.Success -> {
                        _event.send(UiEvents.Loading(false))
                        val data: CaseItem = result.data
                        _event.send(UiEvents.LoadCaseItem(data))
                    }
                }.exhaustive
            }
        }
    }

    sealed class UiEvents {
        data class Loading(val isLoading: Boolean): UiEvents()
        data class FetchNextCase(val caseId: Int): UiEvents()
        data class LoadCaseItem(val caseItem: CaseItem): UiEvents()
    }
}