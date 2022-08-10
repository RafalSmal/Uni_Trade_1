package de.syntaxinstitut.myapplication.ui.kategorienDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.adapter.KategorienDetailAdapter
import de.syntaxinstitut.myapplication.databinding.FragmentKategorienDetailBinding
import de.syntaxinstitut.myapplication.data.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.ui.BasketViewModel

class KategorienDetailFragment : Fragment() {
    private lateinit var binding: FragmentKategorienDetailBinding
    private val basketViewModel: BasketViewModel by activityViewModels()

    private val viewModel: KategorienDetailViewModel by viewModels()
    val args: KategorienDetailFragmentArgs by navArgs()

    /**
     * Layout wird erstellt
     */
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getData(args.kategorieDetail)

        viewModel.angeboteFiltered.observe(
            viewLifecycleOwner,
            Observer {
                binding.kategorienDetailRecycler.adapter =
                    basketViewModel.basket.value?.let { it1 ->

                        KategorienDetailAdapter(it, it1) { partItem: ArtikelData, add: Boolean ->
                            addOrRemoveFromBasket(partItem, add)
                        }
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


