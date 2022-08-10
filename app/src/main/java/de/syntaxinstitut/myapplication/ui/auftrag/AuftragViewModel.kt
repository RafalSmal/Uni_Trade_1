package de.syntaxinstitut.myapplication.ui.auftrag

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import de.syntaxinstitut.myapplication.data.AppRepository
import de.syntaxinstitut.myapplication.data.api.ArtikelApi
import de.syntaxinstitut.myapplication.data.database.ArtikelDatabase
import de.syntaxinstitut.myapplication.data.datamodels.OrdersData
import kotlinx.coroutines.launch

class AuftragViewModel(application: Application): AndroidViewModel(application) {
    private val dataBase = ArtikelDatabase.getDatabase(application)

    private val repository = AppRepository(ArtikelApi,dataBase)

    fun getAllOrders() : List<OrdersData>{
        return repository.getAllFromOrdersdata()
    }

    fun saveOrder(ordersData: OrdersData) {
        viewModelScope.launch {
            repository.insert(ordersData)
        }
    }
}