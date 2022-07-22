package de.syntaxinstitut.myapplication.ui.kategorienDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import de.syntaxinstitut.myapplication.MainViewModel
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.adapter.KategorienDetailAdapter
import de.syntaxinstitut.myapplication.data.DataSource
import de.syntaxinstitut.myapplication.databinding.FragmentKategorienDetailBinding
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.util.BasketViewModel

class KategorienDetailFragment : Fragment() {
    private lateinit var binding: FragmentKategorienDetailBinding
    private val basketViewModel: BasketViewModel by activityViewModels()

    private val viewModel: KategorienDetailViewModel by viewModels()
    val args: KategorienDetailFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_kategorien_detail,
            container,
            false
        )
        // Inflate the layout for this fragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        fun debugLoadArtikel(): List<ArtikelData> {
            var dataSourceLoad = DataSource().loadArtikel()
            Log.d("Hello","dataSourceLoad")
           return dataSourceLoad

        }
        val dataSourceArtikel = debugLoadArtikel()
        //Filtert die Artikel für die KategorienDetail Ansicht

        val filterArtikel =
            viewModel.filterByKategorie(unfilteredList = dataSourceArtikel, args.kategorieDetail)
        val dataset = viewModel.getData(basketViewModel.getBasket())



        binding.kategorienDetailRecycler.adapter = KategorienDetailAdapter(dataset){
            partItem: ArtikelData,add:Boolean-> addOrRemoveFromBasket(partItem,add)
        }

        //  viewModel.articleCounter.observe(viewLifecycleOwner, Observer {

        //  })

    }

    fun addOrRemoveFromBasket(artikelData: ArtikelData, add: Boolean) {
        if (add) {
            basketViewModel.addBasket(artikelData)
        } else {
            basketViewModel.removeBasket(artikelData)
        }
    }

}


