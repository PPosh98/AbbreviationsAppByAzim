package com.example.abbreviationsappbyazim.models


import com.google.gson.annotations.SerializedName

data class AbbreviationsItemModel(
    @SerializedName("lfs")
    val lfs: List<LfModel>,
    @SerializedName("sf")
    val sf: String
)