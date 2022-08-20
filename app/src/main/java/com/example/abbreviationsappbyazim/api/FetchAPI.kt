package com.example.abbreviationsappbyazim.api

import retrofit2.http.GET
import retrofit2.http.Query

interface FetchAPI {
    @GET("dictionary.py")
    suspend fun getAbbreviations(
        @Query("sf") shortForm: String
    )
}