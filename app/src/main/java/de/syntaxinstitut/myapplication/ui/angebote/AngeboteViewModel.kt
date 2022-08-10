package de.syntaxinstitut.myapplication.ui.angebote

import android.app.Application
import androidx.lifecycle.*
import de.syntaxinstitut.myapplication.data.AppRepository
import de.syntaxinstitut.myapplication.data.api.ArtikelApi
import de.syntaxinstitut.myapplication.data.database.ArtikelDatabase
import de.syntaxinstitut.myapplication.data.datamodels.ArtikelData
import kotlinx.coroutines.launch

class AngeboteViewModel(application: Application) : AndroidViewModel(application) {

    private val dataBase = ArtikelDatabase.getDatabase(application)
    private val appRepository = AppRepository(ArtikelApi,dataBase)

    private val _angeboteChanged = MutableLiveData<List<ArtikelData>>()
    val angeboteChanged: LiveData<List<ArtikelData>>
        get() = _angeboteChanged


    /**
     * Holt Daten aus dem Api call
     */
    fun getData(basket: List<ArtikelData>) {

        viewModelScope.launch {
            val allArtikel = appRepository.getFromApi()!!.filter {
                it.price != null
            }

            /**
             * Filtert die Artikel nach Kategorie aus dem Api Call
             */
            val filteredArtikel = mutableListOf<ArtikelData>()
            for (i in allArtikel) {

                if (filteredArtikel.filter { it.category == i.category }.isEmpty()) {
                    filteredArtikel.add(i)
                }
            }

            for (artikel in basket) {
                for (fartikel in filteredArtikel) {
                    if (artikel.productText == fartikel.productText) {
                        fartikel.quantity = artikel.quantity
                    }
                }
            }

            _angeboteChanged.value = filteredArtikel

        }


    }

}