package de.syntaxinstitut.myapplication.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.syntaxinstitut.myapplication.data.remote.ArtikelApi
import de.syntaxinstitut.myapplication.data.remote.ArtikelApiService
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import kotlinx.coroutines.delay

class ArtikelRepository(private val artikelApi : ArtikelApi) {

    private val _artikelListe = MutableLiveData<List<ArtikelData>>()
    val artikelListe : LiveData<List<ArtikelData>>
    get() = _artikelListe

    suspend fun getArtikel(){
        delay(2000)
        _artikelListe.value = artikelApi.retrofitService.getArtikel()
    }
}