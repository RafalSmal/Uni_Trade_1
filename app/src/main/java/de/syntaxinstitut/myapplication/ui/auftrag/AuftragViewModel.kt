package de.syntaxinstitut.myapplication.ui.auftrag

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Index
import de.syntaxinstitut.myapplication.database.ArtikelDatabase
import de.syntaxinstitut.myapplication.database.ArtikelRepository
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.datamodels.OrdersData
import kotlinx.coroutines.launch

class AuftragViewModel(application: Application): AndroidViewModel(application) {
    private val dataBase = ArtikelDatabase.getDatabase(application)

    private val repository = ArtikelRepository(dataBase)

    fun getAllOrders() : List<OrdersData>{
        return repository.getAllFromOrdersdata()
    }

    fun saveOrder(ordersData: OrdersData) {
        viewModelScope.launch {
            repository.insert(ordersData)
        }
    }
}