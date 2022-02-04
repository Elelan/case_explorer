package com.ezio.caseexplorer.core.domain.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CaseItem(
    @Json(name = "answers")
    val answers: List<Answer>,
    @Json(name = "id")
    val id: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "text")
    val text: String
)