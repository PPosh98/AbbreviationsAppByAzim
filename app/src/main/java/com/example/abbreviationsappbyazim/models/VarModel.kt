package com.example.abbreviationsappbyazim.models


import com.google.gson.annotations.SerializedName

data class VarModel(
    @SerializedName("freq")
    val freq: Int,
    @SerializedName("lf")
    val lf: String,
    @SerializedName("since")
    val since: Int
)