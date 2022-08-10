package de.syntaxinstitut.myapplication.ui

import android.app.Application
import androidx.lifecycle.*
import de.syntaxinstitut.myapplication.data.AppRepository
import de.syntaxinstitut.myapplication.data.api.ArtikelApi
import de.syntaxinstitut.myapplication.data.database.ArtikelDatabase
import de.syntaxinstitut.myapplication.data.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.data.datamodels.OrdersData
import kotlinx.coroutines.launch
import java.time.LocalDate

class BasketViewModel(application: Application) : AndroidViewModel(application) {
    val basket = MutableLiveData<MutableList<ArtikelData>>()
    private val dataBase = ArtikelDatabase.getDatabase(application)

    private val repository = AppRepository(ArtikelApi,dataBase)

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



    fun removeBasket(artikelData: ArtikelData) {
        basket.value!!.remove(artikelData)

    }


    fun warenkorbEnde(
        firma: String,
        lieferStrasse: String,
        lieferOrt: String
    ) {
        var gesamtBrutto = 0.0
        for (item in basket.value!!) {
            gesamtBrutto += (item.quantity * item.price!!)
        }

        val order = OrdersData(
            auftragsNr = repository.getCountFromOrdersdata() + 1,
            firma = firma,
            bestellDatum = LocalDate.now().toString(),
            auftragNetto = (gesamtBrutto / 1.19).toFloat(),
            auftragBrutto = gesamtBrutto.toFloat(),
            lieferStrasse = lieferStrasse,
            lieferOrt = lieferOrt,
            articles = basket.value!!.toList()

        )

        viewModelScope.launch {
            repository.insert(order)
        }

    }

    private val _loadingScreen = MutableLiveData<Boolean>()
    val loadingScreen: LiveData<Boolean>
        get() = _loadingScreen

    fun showLoadingScreen (){
        _loadingScreen.value = true
    }

    fun hideLoadingScreen() {
        _loadingScreen.value = false
    }
}


