package de.syntaxinstitut.myapplication.ui.angebote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntaxinstitut.myapplication.api.ApiRepository
import de.syntaxinstitut.myapplication.database.ArtikelRepository
import de.syntaxinstitut.myapplication.data.remote.ArtikelApi
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import kotlinx.coroutines.launch

class AngeboteViewModel(application : Application) : AndroidViewModel(application) {

    private val apiRepository = ApiRepository(ArtikelApi)
    val angeboteChanged = mutableListOf<ArtikelData>()
    var dataBaseRepository = ArtikelRepository.getInstance(application)

    fun getData(basket: List<ArtikelData>): List<ArtikelData> {
        viewModelScope.launch {
            val angebote = apiRepository.getFromApi()

            var found = false

            if (angebote != null) {
                for (artikel in angebote) {
                    for (basketArtikel in basket) {
                        if (artikel.productText == basketArtikel.productText) {
                            angeboteChanged.add(
                                ArtikelData(
                                    artikel.id,
                                    artikel.productText,
                                    artikel.image,
                                    artikel.price,
                                    artikel.category,
                                    basketArtikel.quantity,
                                )
                            )
                            found = true
                            break
                        }
                    }
                    if (!found) {
                        angeboteChanged.add(artikel)
                    } else {
                        found = false
                    }
                    dataBaseRepository.insert(artikel)
                }

            }

        }
        return angeboteChanged
    }



}
