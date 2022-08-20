package com.example.abbreviationsappbyazim.api

import com.example.abbreviationsappbyazim.models.Abbreviations
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FetchAPI {
    @GET("dictionary.py")
    suspend fun getAbbreviations(
        @Query("sf") shortForm: String
    ) : Response<Abbreviations>
}