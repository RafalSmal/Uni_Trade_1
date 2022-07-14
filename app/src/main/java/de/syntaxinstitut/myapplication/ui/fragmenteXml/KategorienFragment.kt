package de.syntaxinstitut.myapplication.ui.fragmenteXml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.adapter.KategorienAdapter
import de.syntaxinstitut.myapplication.data.DataSource
import de.syntaxinstitut.myapplication.data.KategorieDetailEnum
import de.syntaxinstitut.myapplication.databinding.FragmentKategorienBinding
import de.syntaxinstitut.myapplication.datamodels.KategorienData

class KategorienFragment : Fragment() {
    private lateinit var binding: FragmentKategorienBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kategorien, container, false)
        // Inflate the layout for this fragment

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val kategorien = listOf(
            KategorienData("Gemüse", R.drawable.sweets, KategorieDetailEnum.GEMUESE),
            KategorienData("Obst", R.drawable.sweets, KategorieDetailEnum.OBST),
            KategorienData("Fleisch", R.drawable.sweets, KategorieDetailEnum.FLEISCH),
            KategorienData("Sweets", R.drawable.sweets, KategorieDetailEnum.SWEETS),
            KategorienData("Tiefkühl", R.drawable.pommes, KategorieDetailEnum.TIEFKUEHL),
            KategorienData("Getränke", R.drawable.sweets, KategorieDetailEnum.GETRAENKE)

        )
        binding.kategorieRecycler.adapter = KategorienAdapter(kategorien) {
            partItem: KategorienData->
            partItemClicked(partItem)

        }

    }

    fun partItemClicked(partItem:KategorienData){
        findNavController().navigate(KategorienFragmentDirections.actionKategorienFragmentToKategorienDetailFragment(partItem.kategorie))
    }
}