package com.ezio.caseexplorer.core.domain.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScenarioItem(
    @Json(name = "caseid")
    val caseId: Int,
    @Json(name = "text")
    val text: String
)