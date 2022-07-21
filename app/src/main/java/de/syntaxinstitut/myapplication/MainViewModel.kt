package de.syntaxinstitut.myapplication

import android.app.Application
import android.content.ClipData
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Database
import de.syntaxinstitut.myapplication.data.DataSource
import de.syntaxinstitut.myapplication.data.KategorieDetailEnum
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.local.Repository
import de.syntaxinstitut.myapplication.local.getDatabase
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

class MainViewModel(application: Application) : ViewModel() {


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

    enum class ApiStatus {LOADING,ERROR,DONE}

    private val _loading = MutableLiveData<ApiStatus>()
    val loading: LiveData<ApiStatus>
        get() = _loading

    private val dataBase = getDatabase(application)

    private val repository = Repository(dataBase)

    fun insertArtikel(artikelData: ArtikelData) {
        viewModelScope.launch {
            repository.insert(artikelData)
        }
    }

    fun delete(artikelData: ArtikelData) {
        viewModelScope.launch {
            repository.deleteById(artikelData)
        }
    }

    fun update(artikelData: ArtikelData) {
        viewModelScope.launch {
            repository.update(artikelData)
        }
    }

//    private fun loadData() {
//        viewModelScope.launch {
//            _loading.value = ApiStatus.LOADING
//            try {
//                repository.
//                _loading.value = ApiStatus.DONE
//            } catch (e: Exception) {
//                Log.e(TAG, "Error loading Data from API: $e")
//                _loading.value = ApiStatus.ERROR
//            }
//        }
//    }

}

