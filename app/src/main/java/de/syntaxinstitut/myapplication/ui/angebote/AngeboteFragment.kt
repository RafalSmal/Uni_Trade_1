package de.syntaxinstitut.myapplication.ui.angebote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.adapter.AngeboteAdapter
import de.syntaxinstitut.myapplication.databinding.FragmentAngeboteNewBinding
import de.syntaxinstitut.myapplication.data.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.ui.BasketViewModel


class AngeboteFragment : Fragment() {

    private lateinit var binding: FragmentAngeboteNewBinding
    private lateinit var basketViewModel: BasketViewModel
    private val viewModel: AngeboteViewModel by viewModels()

    /**
     * Diese Funktiuon wird zu beginn des Lifecycles eingefügt
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        basketViewModel = ViewModelProvider(requireActivity())[BasketViewModel::class.java]

    }

    /**
     * Layout wird "aufgeblasen"
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_angebote_new, container, false)
        return binding.root
    }

    /**
     * Hauptfunktionalität
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Der SnapHelper sorgt dafür,
        dass die RecyclerView immer auf das aktuelle List Item springt
         */
        val helper: SnapHelper = PagerSnapHelper()
        helper.attachToRecyclerView(binding.angeboteNew)


        /**Diese Funktion erstellt eine Courountine um von der Api alle Artikel zu laden
         *
         */
        viewModel.getData(basketViewModel.basket.value!!.toList())

        viewModel.angeboteChanged.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                binding.angeboteNew.adapter =
                    AngeboteAdapter(requireContext(), it) { partItem: ArtikelData, add: Boolean ->
                        addOrRemoveFromBasket(partItem, add)
                    }
            }
        )
    }


    fun addOrRemoveFromBasket(artikelData: ArtikelData, add: Boolean) {
        if (add) {
            basketViewModel.addBasket(artikelData)
        } else {
            basketViewModel.removeBasket(artikelData)
        }
    }
}






