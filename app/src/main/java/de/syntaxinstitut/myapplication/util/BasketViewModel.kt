package de.syntaxinstitut.myapplication.util

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.syntaxinstitut.myapplication.database.ArtikelDatabase
import de.syntaxinstitut.myapplication.database.ArtikelRepository
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.datamodels.OrdersData
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class BasketViewModel(application: Application): AndroidViewModel(application) {
    val basket = MutableLiveData<MutableList<ArtikelData>>()
    private val dataBase = ArtikelDatabase.getDatabase(application)

    private val repository = ArtikelRepository(dataBase)

    init {
        basket.value = mutableListOf()
    }

    fun addBasket(artikelData: ArtikelData) {
        for (artikel in basket.value!!) {
            if (artikel.productText == artikelData.productText) {
                artikel.quantity = artikelData.quantity
                return
            }
        }
        basket.value!!.add(artikelData)
    }


    //TODO  Wird in die Datenbank eingefügt


    fun removeBasket(artikelData: ArtikelData) {
        basket.value!!.remove(artikelData)

        //TODO Datenbank einfügen


    }


    fun warenkorbEnde(){
        var gesamtBrutto = 0.0
        for (item in basket.value!!){
            gesamtBrutto += (item.quantity * item.price!!)
        }

        val order = OrdersData(
            auftragsNr = (repository.getCountFromOrdersdata() + 1 ).toString(),
            bestellDatum = LocalDate.now().toString(),
            auftragNetto = gesamtBrutto / 1.19,
            auftragBrutto = gesamtBrutto,
            lieferStrasse = "Max Musterweg 3",
            lieferOrt = "24356 Musterstadt"

        )

        viewModelScope.launch {
            repository.insert(order)
        }

    }
}


