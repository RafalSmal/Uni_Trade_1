package de.syntaxinstitut.myapplication.ui.kategorienDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import de.syntaxinstitut.myapplication.api.ApiRepository
import de.syntaxinstitut.myapplication.data.KategorieDetailEnum
import de.syntaxinstitut.myapplication.data.remote.ArtikelApi
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import kotlinx.coroutines.launch


class KategorienDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val apiRepository = ApiRepository(ArtikelApi)

    private val _angeboteFiltered = MutableLiveData<List<ArtikelData>>()
    val angeboteFiltered: LiveData<List<ArtikelData>>
        get() = _angeboteFiltered

   // val artikelList = repository.artikelListe

    fun getData(kategorie:KategorieDetailEnum) {
        viewModelScope.launch {
            _angeboteFiltered.value = apiRepository.getFromApi()!!.filter{
                it.category == kategorie && it.price != null
            }
        }
    }
}
