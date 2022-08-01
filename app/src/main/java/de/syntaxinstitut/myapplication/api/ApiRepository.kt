package de.syntaxinstitut.myapplication.api

import android.util.Log
import de.syntaxinstitut.myapplication.data.remote.ArtikelApi
import de.syntaxinstitut.myapplication.datamodels.ArtikelData

class ApiRepository(
    private val api : ArtikelApi
){
    suspend fun getFromApi() : List<ArtikelData>?{
        try {
            return api.retrofitService.getArtikel()
        } catch (e:Exception){
            Log.d("ApiRepo",e.toString())
        }
        return null
    }
}



//package de.syntaxinstitut.myapplication.local
//
//import android.content.Context
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.room.Database
//import de.syntaxinstitut.myapplication.data.remote.ArtikelApiService
//import de.syntaxinstitut.myapplication.datamodels.ArtikelData
//
//class Repository(
//    private val database: ArtikelDatabase,
//    private val api: ArtikelApiService
//) {
//
//    var artikelListe: LiveData<List<ArtikelData>> = database.artikelPoolDao.getAll()
//
//    suspend fun deleteById(artikelData: ArtikelData) {
//        try {
//            database.artikelPoolDao.deleteById(artikelData.id)
//        } catch (
//            e: Exception
//        ) {
//            Log.d("Repository", "Dieser  Artikel ist nicht mehr verfügbar $e")
//        }
//
//    }
//
//    suspend fun update(artikelData: ArtikelData) {
//        try {
//            database.artikelPoolDao.update(artikelData)
//        } catch (
//            e: Exception
//        ) {
//            Log.d("Repository", "Update hat nicht funktioniert :$e")
//        }
//    }
//
//    suspend fun insert(artikelData: ArtikelData) {
//        try {
//            database.artikelPoolDao.insertItem(artikelData)
//        } catch (
//            e: Exception
//        ) {
//            Log.d("Repository", "Insert nicht funktioniert$e")
//        }
//    }
//
//
//}
//

