package com.example.abbreviationsappbyazim.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AbbreviationsDAO {

    @Query("SELECT * FROM abbreviations WHERE shortForm = :shortForm ORDER BY generatedId ASC")
    fun readAbbreviationsFromDb(shortForm: String) : Flow<List<AbbreviationsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAbbreviationToDb(abbreviationsEntity: AbbreviationsEntity)
}