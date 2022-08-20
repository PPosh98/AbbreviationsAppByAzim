package com.example.abbreviationsappbyazim.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.abbreviationsappbyazim.models.Abbreviations

@Entity(tableName = "abbreviations")
class AbbreviationsEntity(val abbreviationsModel: Abbreviations) {
    @PrimaryKey(autoGenerate = true)
    var generatedId: Int = 0


}