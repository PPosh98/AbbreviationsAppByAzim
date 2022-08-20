package com.example.abbreviationsappbyazim.repository

import com.example.abbreviationsappbyazim.api.FetchAPI
import com.example.abbreviationsappbyazim.models.Abbreviations
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val fetchAPI: FetchAPI) : Repository {
    override suspend fun getAbbreviations(shortForm: String): Response<Abbreviations> =
        fetchAPI.getAbbreviations(shortForm)
}