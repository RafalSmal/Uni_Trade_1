package de.syntaxinstitut.myapplication.ui.kategorienDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import de.syntaxinstitut.myapplication.api.ApiRepository
import de.syntaxinstitut.myapplication.database.ArtikelRepository
import de.syntaxinstitut.myapplication.data.KategorieDetailEnum
import de.syntaxinstitut.myapplication.data.remote.ArtikelApi
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.database.ArtikelDatabase
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
                it.category == kategorie
            }
        }
    }
}
       // val kategorienChanged = mutableListOf<ArtikelData>()
       // var found = false

     //   for (artikel in kategorienDetail) {
//            for (basketArtikel in basket) {
//                if (artikel.productText == basketArtikel.productText) {
//                    kategorienChanged.add(
//                        ArtikelData(
//                            artikel.id,
//                            artikel.productText,
//                            artikel.image,
//                            artikel.price,
//                            artikel.category,
//                            basketArtikel.quantity,
//                        )
//                    )
//                    found = true
//                    break
//                }
//            }
//            if (!found) {
//                kategorienChanged.add(artikel)
//            } else {
//                found = false
//            }
//        }
//        return kategorienChanged
//
//
//    }

//    fun filterByKategorie(
//        unfilteredList: List<ArtikelData>,
//        filter: KategorieDetailEnum
//    ): List<ArtikelData> {
//        val filteredList = mutableListOf<ArtikelData>()
//        for (item in unfilteredList) {
//            if (item.category == filter) {
//                filteredList.add(item)
//            }
//        }
//        return filteredList
//    }
//
//}

