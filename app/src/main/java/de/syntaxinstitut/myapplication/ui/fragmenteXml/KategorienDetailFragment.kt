package de.syntaxinstitut.myapplication.ui.fragmenteXml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import de.syntaxinstitut.myapplication.MainViewModel
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.adapter.KategorienDetailAdapter
import de.syntaxinstitut.myapplication.data.DataSource
import de.syntaxinstitut.myapplication.databinding.FragmentKategorienBinding
import de.syntaxinstitut.myapplication.databinding.FragmentKategorienDetailBinding
import de.syntaxinstitut.myapplication.datamodels.KategorienData

class KategorienDetailFragment : Fragment() {
    private lateinit var binding: FragmentKategorienDetailBinding

    private val viewModel: MainViewModel by viewModels()
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

        val dataSourceArtikel = DataSource().loadArtikel()
        //Filtert die Artikel für die KategorienDetail Ansicht

        val filterArtikel =
            viewModel.filterByKategorie(unfilteredList = dataSourceArtikel, args.kategorieDetail)

        binding.kategorienDetailRecycler.adapter = KategorienDetailAdapter(filterArtikel) {

        }

      //  viewModel.articleCounter.observe(viewLifecycleOwner, Observer {

      //  })

    }
}


