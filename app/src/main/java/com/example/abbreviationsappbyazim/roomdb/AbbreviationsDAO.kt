package com.example.abbreviationsappbyazim.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AbbreviationsDAO {

    @Query("SELECT * FROM abbreviations ORDER BY generatedId ASC")
    fun readAbbreviationsFromDb() : Flow<List<AbbreviationsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAbbreviationInsideDb(abbreviationsEntity: AbbreviationsEntity)
}