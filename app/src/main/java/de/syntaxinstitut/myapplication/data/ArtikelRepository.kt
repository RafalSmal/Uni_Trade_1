package de.syntaxinstitut.myapplication.data

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.syntaxinstitut.myapplication.data.remote.ArtikelApi
import de.syntaxinstitut.myapplication.data.remote.ArtikelApiService
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import kotlinx.coroutines.delay
import java.lang.Exception

class ArtikelRepository(private val api : ArtikelApi) {

    private val _artikelListe = MutableLiveData<List<ArtikelData>>()
    val artikelListe : LiveData<List<ArtikelData>>
    get() = _artikelListe

    suspend fun getArtikel(){
        Log.e(TAG,"ladet Artikel")
        try{
            val artikelList = api.retrofitService.getArtikel()
        }catch (e: Exception){
            Log.e(TAG,"Error loading Data from API: $e")
        }
        _artikelListe.value = api.retrofitService.getArtikel()
    }
}