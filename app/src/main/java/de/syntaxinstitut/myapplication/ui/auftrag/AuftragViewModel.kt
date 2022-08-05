package de.syntaxinstitut.myapplication.ui.auftrag

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import de.syntaxinstitut.myapplication.database.ArtikelDatabase
import de.syntaxinstitut.myapplication.database.ArtikelRepository
import de.syntaxinstitut.myapplication.datamodels.OrdersData

class AuftragViewModel(application: Application): AndroidViewModel(application) {
    private val dataBase = ArtikelDatabase.getDatabase(application)

    private val repository = ArtikelRepository(dataBase)

    fun getAllOrders() : List<OrdersData>{
        return repository.getAllFromOrdersdata()
    }
}