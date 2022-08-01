package de.syntaxinstitut.myapplication.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import java.lang.Exception


class ArtikelRepository(
    private val database: ArtikelDatabase,
) {

    companion object {
        private var databaseRepository: ArtikelRepository? = null

        fun getInstance(context: Context): ArtikelRepository =
            databaseRepository ?: buildRepository(
                ArtikelDatabase.getDatabase(context.applicationContext)
            ).also {
                databaseRepository = it
            }

        private fun buildRepository(database: ArtikelDatabase): ArtikelRepository =
            ArtikelRepository(database)
    }

    fun getArtikel(): List<ArtikelData> {

        return database.artikelPoolDao.getAll()

    }

//    suspend fun getArtikel() {
//        Log.e(TAG, "ladet Artikel")
//        try {
//            val artikelList = api.retrofitService.getArtikel()
//            _artikelListe.value = artikelList
//        } catch (e: Exception) {
//            Log.e(TAG, "Error loading Data from API: $e")
//        }
//
//    }

    suspend fun deleteById(artikelData: ArtikelData) {
        try {
            database.artikelPoolDao.deleteById(artikelData.id)
        } catch (
            e: Exception
        ) {
            Log.d("Repository", "Dieser  Artikel ist nicht mehr verfügbar $e")
        }

    }

    suspend fun update(artikelData: ArtikelData) {
        try {
            database.artikelPoolDao.update(artikelData)
        } catch (
            e: Exception
        ) {
            Log.d("Repository", "Update hat nicht funktioniert :$e")
        }
    }

    suspend fun insert(artikelData: ArtikelData) {
        try {
            database.artikelPoolDao.insertItem(artikelData)
        } catch (
            e: Exception
        ) {
            Log.d("Repository", "Insert nicht funktioniert$e")
        }
    }


}

