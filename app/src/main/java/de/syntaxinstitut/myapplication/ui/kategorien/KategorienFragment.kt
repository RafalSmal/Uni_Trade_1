package de.syntaxinstitut.myapplication.ui.kategorien

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.adapter.KategorienAdapter
import de.syntaxinstitut.myapplication.data.KategorieDetailEnum
import de.syntaxinstitut.myapplication.databinding.FragmentKategorienBinding
import de.syntaxinstitut.myapplication.data.datamodels.KategorienData
import de.syntaxinstitut.myapplication.ui.kategorienDetail.KategorienDetailViewModel

/**
 * Dieses Fragment enthält die UI Ansicht der Kategorien
 */
class KategorienFragment : Fragment() {

    /* ---------- Globale Variablen ---------- */

    private val viewModel: KategorienDetailViewModel by viewModels()

    private lateinit var binding: FragmentKategorienBinding


    /* ---------- Lifecycle ---------- */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kategorien, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val kategorien = listOf(
            KategorienData("Gemüse", R.drawable.vegtable, KategorieDetailEnum.Gemüse,),
            KategorienData("Obst", R.drawable.fruits, KategorieDetailEnum.Obst),
            KategorienData("Fleisch", R.drawable.meat, KategorieDetailEnum.Fleisch),
            KategorienData("Sweets", R.drawable.sweets, KategorieDetailEnum.Süßigkeiten),
            KategorienData("Tiefkühl", R.drawable.benandjerrys, KategorieDetailEnum.Tiefkühl),
            KategorienData("Getränke", R.drawable.drinks, KategorieDetailEnum.Getränke)

        )
        binding.kategorieRecycler.adapter =
            KategorienAdapter(kategorien) { partItem: KategorienData ->
                partItemClicked(partItem)

            }

    }

    /**
     * Wechselt zu Kategorien-Detail-Fragment bei Klick auf Item
      */

    fun partItemClicked(partItem: KategorienData) {
        findNavController().navigate(
            KategorienFragmentDirections.actionKategorienFragmentToKategorienDetailFragment(
                partItem.kategorie
            )
        )
    }
}