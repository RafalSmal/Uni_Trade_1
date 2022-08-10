package de.syntaxinstitut.myapplication.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import de.syntaxinstitut.myapplication.datamodels.ArtikelData

class Converters {
    @TypeConverter
    fun listOfItemsToString(items: List<ArtikelData>): String? {
        val gson = Gson()
        return gson.toJson(items)
    }

    @TypeConverter
    fun fromString(value: String?): List<ArtikelData> {
        val listType = object : TypeToken<ArrayList<ArtikelData?>?>() { }.type
        return Gson().fromJson(value, listType)
    }
}