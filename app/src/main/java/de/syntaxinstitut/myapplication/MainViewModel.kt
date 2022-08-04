package de.syntaxinstitut.myapplication

import android.app.Application
import androidx.lifecycle.*
import de.syntaxinstitut.myapplication.database.ArtikelRepository
import de.syntaxinstitut.myapplication.data.KategorieDetailEnum
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.database.ArtikelDatabase.Companion.getDatabase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {


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


    private val _loadingScreen = MutableLiveData<Boolean>()
    val loadingScreen: LiveData<Boolean>
        get() = _loadingScreen

    fun showLoadingScreen (){
    _loadingScreen.value = true
    }

    fun hideLoadingScreen() {
        _loadingScreen.value = false
    }


    private val dataBase = getDatabase(application)

    private val repository = ArtikelRepository(dataBase)

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

