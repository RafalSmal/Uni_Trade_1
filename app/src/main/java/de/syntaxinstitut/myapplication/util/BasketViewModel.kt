package de.syntaxinstitut.myapplication.util

import android.util.Log
import androidx.lifecycle.ViewModel
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import java.util.*

class BasketViewModel : ViewModel(){
    private val basket = mutableListOf<ArtikelData>()

    fun addBasket(artikelData: ArtikelData) {
        var found = false

        for (artikel in basket){
            if (artikel.productText == artikelData.productText){
               artikel.quantity = artikelData.quantity
                found = true
            }
        }
        if (found){
            return
        }

        basket.add(artikelData)

    }

    fun removeBasket(artikelData: ArtikelData) {
        for (artikel in basket ){
            if (artikel.productText== artikelData.productText){
                basket.remove(artikel)
                return
            }
        }
    }

    fun getBasket(): List<ArtikelData> {
        return basket
    }

}