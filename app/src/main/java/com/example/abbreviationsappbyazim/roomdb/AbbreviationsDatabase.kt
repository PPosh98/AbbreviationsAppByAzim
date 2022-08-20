package com.example.abbreviationsappbyazim.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [AbbreviationsEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(AbbreviationTypeConverter::class)
abstract class AbbreviationsDatabase : RoomDatabase() {
    abstract fun abbreviationsDAO() : AbbreviationsDAO
}