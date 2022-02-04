package com.ezio.caseexplorer.ui.feature_scenarios

import com.ezio.caseexplorer.core.ui.MessageType
import com.ezio.caseexplorer.core.domain.models.ScenarioItem

sealed class UiStateEvents {
    data class Loading(val isLoading: Boolean): UiStateEvents()
    data class UiMessage(val messageType: MessageType, val message: String)
    data class LoadScenarioList(val data: List<ScenarioItem>): UiStateEvents()
}
