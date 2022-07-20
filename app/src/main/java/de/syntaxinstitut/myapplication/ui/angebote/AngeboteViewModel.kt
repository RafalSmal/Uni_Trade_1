package de.syntaxinstitut.myapplication.ui.angebote

import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.ViewModel
import de.syntaxinstitut.myapplication.data.DataSource
import de.syntaxinstitut.myapplication.datamodels.ArtikelData

class AngeboteViewModel : ViewModel() {
    fun getData(basket: List<ArtikelData>): List<ArtikelData> {
        val angebote = DataSource().loadArtikel()
        val angeboteChanged = mutableListOf<ArtikelData>()
        var found = false

        for (artikel in angebote) {
            for (basketArtikel in basket) {
                if (artikel.productText == basketArtikel.productText) {
                    angeboteChanged.add(
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
            if (!found){
                angeboteChanged.add(artikel)
            }else{
                found=false
            }
        }
        return angeboteChanged
    }

}
