package de.syntaxinstitut.myapplication.ui.kategorienDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import de.syntaxinstitut.myapplication.database.ArtikelRepository
import de.syntaxinstitut.myapplication.data.KategorieDetailEnum
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.database.ArtikelDatabase


class KategorienDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ArtikelRepository(ArtikelDatabase.getDatabase(application))

   // val artikelList = repository.artikelListe

    fun getData(basket: List<ArtikelData>) {
    //    val kategorienDetail = DataSource().loadArtikel()
        val kategorienChanged = mutableListOf<ArtikelData>()
        var found = false

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
    }

    fun getData() {
            repository.getArtikel()
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

