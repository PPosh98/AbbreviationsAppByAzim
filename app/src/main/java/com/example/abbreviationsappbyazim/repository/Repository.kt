package com.example.abbreviationsappbyazim.repository

import com.example.abbreviationsappbyazim.models.Abbreviations
import com.example.abbreviationsappbyazim.roomdb.AbbreviationsEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
    suspend fun getAbbreviationsFromAPI(shortForm: String) : Response<Abbreviations>

    suspend fun getAbbreviationsFromDB(shortForm: String) : Flow<List<AbbreviationsEntity>>

    suspend fun addAbbreviationsToDB(abbreviationsEntity: AbbreviationsEntity)

}