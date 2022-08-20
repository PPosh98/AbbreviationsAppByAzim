package com.example.abbreviationsappbyazim.roomdb

import androidx.room.TypeConverter
import com.example.abbreviationsappbyazim.models.Abbreviations
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AbbreviationTypeConverter {

    var gson = Gson()

    @TypeConverter
    fun abbreviationsToString(abbreviations: Abbreviations): String = gson.toJson(abbreviations)

    @TypeConverter
    fun stringToAbbreviations(data: String): Abbreviations {
        val listType = object : TypeToken<Abbreviations>() {}.type
        return gson.fromJson(data, listType)
    }
}