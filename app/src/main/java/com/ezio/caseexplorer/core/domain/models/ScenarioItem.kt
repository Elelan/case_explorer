package com.ezio.caseexplorer.core.domain.models

import com.google.gson.annotations.SerializedName


data class ScenarioItem(
    @SerializedName("caseid")
    val caseId: Int,
    @SerializedName("text")
    val text: String
)