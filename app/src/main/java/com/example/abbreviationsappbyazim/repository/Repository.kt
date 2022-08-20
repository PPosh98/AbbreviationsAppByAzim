package com.example.abbreviationsappbyazim.repository

import com.example.abbreviationsappbyazim.models.Abbreviations
import retrofit2.Response

interface Repository {
    suspend fun getAbbreviations(shortForm: String) : Response<Abbreviations>

}