package com.ezio.caseexplorer.core.domain.models

import com.google.gson.annotations.SerializedName

data class Answer(
    @SerializedName("caseid")
    val caseid: Int,
    @SerializedName("text")
    val text: String
)