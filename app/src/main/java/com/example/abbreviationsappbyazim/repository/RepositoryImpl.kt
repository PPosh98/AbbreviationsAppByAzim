package com.example.abbreviationsappbyazim.repository

import android.util.Log
import com.example.abbreviationsappbyazim.api.FetchAPI
import com.example.abbreviationsappbyazim.models.Abbreviations
import com.example.abbreviationsappbyazim.roomdb.AbbreviationsDAO
import com.example.abbreviationsappbyazim.roomdb.AbbreviationsEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val fetchAPI: FetchAPI, private val abbreviationsDAO: AbbreviationsDAO) : Repository {
    override suspend fun getAbbreviationsFromAPI(shortForm: String): Response<Abbreviations> =
        fetchAPI.getAbbreviations(shortForm)

    override fun getAbbreviationsFromDB(shortForm: String): Flow<List<AbbreviationsEntity>> {
        Log.i("read", "This is also being executed $shortForm")
        return abbreviationsDAO.readAbbreviationsFromDb(shortForm)
    }


    override suspend fun addAbbreviationsToDB(abbreviationsEntity: AbbreviationsEntity) =
        abbreviationsDAO.insertAbbreviationToDb(abbreviationsEntity)
}