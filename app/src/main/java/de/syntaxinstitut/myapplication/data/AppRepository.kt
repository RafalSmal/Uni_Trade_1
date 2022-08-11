package de.syntaxinstitut.myapplication.data

import android.content.Context
import android.util.Log
import de.syntaxinstitut.myapplication.data.api.ArtikelApi
import de.syntaxinstitut.myapplication.data.database.ArtikelDatabase
import de.syntaxinstitut.myapplication.data.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.data.datamodels.OrdersData


class AppRepository(
    private val api: ArtikelApi,
    private val database: ArtikelDatabase

) {

    /**
     * Diese Funktion holt eine Liste von Artikeln per Api - Call
     */
    suspend fun getFromApi(): List<ArtikelData>? {
        try {
            return api.retrofitService.getArtikel()
        } catch (e: Exception) {
            Log.d("ApiRepo", e.toString())
        }
        return null
    }

    companion object {
        private var databaseRepository: AppRepository? = null

        fun getInstance(context: Context): AppRepository =
            databaseRepository ?: buildRepository(
                ArtikelDatabase.getDatabase(context.applicationContext)
            ).also {
                databaseRepository = it
            }

        private fun buildRepository(database: ArtikelDatabase): AppRepository =
            AppRepository(ArtikelApi,database)
    }

    /**
     * Datenbankfunktionen
     */
    fun getAllFromOrdersdata(): List<OrdersData>{
        return  database.artikelPoolDao.getAllFromOrdersdata()
    }

    suspend fun insert(itemData: OrdersData){
        database.artikelPoolDao.insertItem(itemData)
    }

    fun getCountFromOrdersdata () : Int{
        return database.artikelPoolDao.getCountFromOrdersdata()
    }
}