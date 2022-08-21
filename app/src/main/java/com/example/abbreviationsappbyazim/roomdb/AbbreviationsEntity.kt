package com.example.abbreviationsappbyazim.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.abbreviationsappbyazim.models.Abbreviations
import com.example.abbreviationsappbyazim.models.LfModel

@Entity(tableName = "abbreviations")
class AbbreviationsEntity(val abbreviationsModel: Abbreviations) {
    @PrimaryKey(autoGenerate = true)
    var generatedId: Int = 0
    var sf: String = abbreviationsModel[0].sf
    var lf: String = abbreviationsModel[0].lfs.toString()
}