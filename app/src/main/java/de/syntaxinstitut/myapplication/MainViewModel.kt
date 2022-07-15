package de.syntaxinstitut.myapplication

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.syntaxinstitut.myapplication.data.DataSource
import de.syntaxinstitut.myapplication.data.KategorieDetailEnum
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import java.util.*

class MainViewModel : ViewModel() {
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

}

