package de.syntaxinstitut.myapplication.ui.angebote

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import de.syntaxinstitut.myapplication.api.ApiRepository
import de.syntaxinstitut.myapplication.database.ArtikelRepository
import de.syntaxinstitut.myapplication.data.remote.ArtikelApi
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import kotlinx.coroutines.launch

class AngeboteViewModel(application: Application) : AndroidViewModel(application) {

    private val apiRepository = ApiRepository(ArtikelApi)

    private val _angeboteChanged = MutableLiveData<List<ArtikelData>>()
    val angeboteChanged: LiveData<List<ArtikelData>>
        get() = _angeboteChanged
    // var dataBaseRepository = ArtikelRepository.getInstance(application)

    fun getData(basket: List<ArtikelData>) {
        viewModelScope.launch {
            var allArtikel = apiRepository.getFromApi()!!.filter {
                it.price != null
            }
            var filteredArtikel = mutableListOf<ArtikelData>()
            for (i in allArtikel) {
                Log.d("Filter",i.toString())
                if(filteredArtikel.filter {it.category == i.category}.isEmpty()){
                    filteredArtikel.add(i)
                }
                Log.d("Filter",filteredArtikel.toString())
            }

            for (artikel in basket){
                for (fartikel in filteredArtikel){
                    if (artikel.productText == fartikel.productText){
                        fartikel.quantity = artikel.quantity
                    }
                }
            }

            _angeboteChanged.value = filteredArtikel


//            _angeboteChanged.value = apiRepository.getFromApi()!!.filter {
//                it.price != null
//            }

//            var found = false
//
//            if (angebote != null) {
//                for (artikel in angebote) {
//                    for (basketArtikel in basket) {
//                        if (artikel.productText == basketArtikel.productText) {
//                            angeboteChanged.add(
//                                ArtikelData(
//                                    artikel.id,
//                                    artikel.productText,
//                                    artikel.image,
//                                    artikel.price,
//                                    artikel.category,
//                                    basketArtikel.quantity,
//                                )
//                            )
//                            found = true
//                            break
//                        }
//                    }
//                    if (!found) {
//                        angeboteChanged.add(artikel)
//                    } else {
//                        found = false
//                    }
//                    dataBaseRepository.insert(artikel)
//                }
//
//            }
//
//        }
//        return angeboteChanged
        }


    }

}