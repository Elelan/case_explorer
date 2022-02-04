package com.ezio.caseexplorer.core.domain.models

import com.google.gson.annotations.SerializedName


data class CaseItem(
    @SerializedName("answers")
    val answers: List<Answer>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("text")
    val text: String
)