package de.syntaxinstitut.myapplication.ui.kategorienDetail

import androidx.lifecycle.ViewModel
import de.syntaxinstitut.myapplication.data.DataSource
import de.syntaxinstitut.myapplication.data.KategorieDetailEnum
import de.syntaxinstitut.myapplication.datamodels.ArtikelData

class KategorienDetailViewModel : ViewModel() {
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