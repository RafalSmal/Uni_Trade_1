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

/**
 * Diese Klasse enthält die Logik der App
 */
class BasketViewModel(application: Application) : AndroidViewModel(application) {
    val basket = MutableLiveData<MutableList<ArtikelData>>()
    private val dataBase = ArtikelDatabase.getDatabase(application)

    private val repository = AppRepository(ArtikelApi,dataBase)

    init {
        basket.value = mutableListOf()
    }

    /**
     * Fügt Artikel in den Warenkorb hinzu+
     *
     * @param artikelData Der zu speichernde Artikel
     */
    fun addBasket(artikelData: ArtikelData) {
        for (artikel in basket.value!!) {
            if (artikel.productText == artikelData.productText) {
                artikel.quantity = artikelData.quantity
                return
            }
        }
        basket.value!!.add(artikelData)
    }

    /**
     * Entfernt den Artikel aus dem Warenkorb
     */
    fun removeBasket(artikelData: ArtikelData) {
        basket.value!!.remove(artikelData)

    }


    /**
     * Schließt den Warenkorb ab, speichert Auftrag in Datenbank
     *
     * @param firma Gibt die Firma, die die Bestellung ausführt an
     * @param lieferStrasse Gibt die Lieferadresse an
     * @param lieferOrt Gibt die Stadt an
     */
    fun warenkorbEnde(
        firma: String,
        lieferStrasse: String,
        lieferOrt: String
    ) {
        // Berechne Gesamtbrutto
        var gesamtBrutto = 0.0
        for (item in basket.value!!) {
            gesamtBrutto += (item.quantity * item.price!!)
        }

        // Erstelle Order
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

        // Füge in Datenbank ein
        viewModelScope.launch {
            repository.insert(order)
        }

    }

    // Macht die BottomNavBar (un)sichtbar um den Startscreen anzuzeign

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


