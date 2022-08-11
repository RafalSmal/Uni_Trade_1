package de.syntaxinstitut.myapplication.ui.kategorienDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import de.syntaxinstitut.myapplication.data.AppRepository
import de.syntaxinstitut.myapplication.data.KategorieDetailEnum
import de.syntaxinstitut.myapplication.data.api.ArtikelApi
import de.syntaxinstitut.myapplication.data.database.ArtikelDatabase
import de.syntaxinstitut.myapplication.data.datamodels.ArtikelData
import kotlinx.coroutines.launch

/**
 * Diese Klasse enthaält die Logik, Datenbank abfragen sowie den Api - Call
 */
class KategorienDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val dataBase = ArtikelDatabase.getDatabase(application)
    private val appRepository = AppRepository(ArtikelApi,dataBase)

    private val _angeboteFiltered = MutableLiveData<List<ArtikelData>>()
    val angeboteFiltered: LiveData<List<ArtikelData>>
        get() = _angeboteFiltered


    /**
     * Diese Funktion holt die Daten mit Hilfe des Api Calls
     */
    fun getData(kategorie:KategorieDetailEnum) {
        viewModelScope.launch {
            _angeboteFiltered.value = appRepository.getFromApi()!!.filter{
                it.category == kategorie && it.price != null
            }
        }
    }
}
