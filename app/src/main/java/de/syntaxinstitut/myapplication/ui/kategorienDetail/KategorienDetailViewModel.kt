package de.syntaxinstitut.myapplication.ui.kategorienDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import de.syntaxinstitut.myapplication.data.ArtikelRepository
import de.syntaxinstitut.myapplication.data.DataSource
import de.syntaxinstitut.myapplication.data.KategorieDetailEnum
import de.syntaxinstitut.myapplication.data.remote.ArtikelApi
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import kotlinx.coroutines.launch


class KategorienDetailViewModel(application: Application) : AndroidViewModel(application){

private val repository = ArtikelRepository(ArtikelApi)

val artikelList = repository.artikelListe

    fun getData (basket: List<ArtikelData>) : List<ArtikelData>{
        val kategorienDetail = DataSource().loadArtikel()
        val kategorienChanged = mutableListOf<ArtikelData>()
        var found = false

        for (artikel in kategorienDetail) {
            for (basketArtikel in basket){
                if(artikel.productText == basketArtikel.productText){
                    kategorienChanged.add(
                        ArtikelData(
                            artikel.id,
                            artikel.productText,
                            basketArtikel.quantity,
                            artikel.image,
                            artikel.price,
                            artikel.category
                        )
                    )
                    found = true
                    break
                }
            }
            if(!found){
                kategorienChanged.add(artikel)
            }else{
                found=false
            }
        }
        return kategorienChanged


        fun loadData() {
            viewModelScope.launch {
                repository.getArtikel()
            }
        }
    }


    fun filterByKategorie(
        unfilteredList: List<ArtikelData>,
        filter: KategorieDetailEnum
    ): List<ArtikelData> {
        val filteredList = mutableListOf<ArtikelData>()
        for (item in unfilteredList) {
            if (item.category == filter) {
                filteredList.add(item)
            }
        }
        return filteredList
    }

}

