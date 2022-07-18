package de.syntaxinstitut.myapplication.ui.angebote

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.adapter.AngeboteAdapter
import de.syntaxinstitut.myapplication.adapter.KategorienDetailAdapter
import de.syntaxinstitut.myapplication.data.DataSource
import de.syntaxinstitut.myapplication.databinding.FragmentAngeboteNewBinding
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.util.BasketViewModel
import java.util.*


class AngeboteFragment : Fragment() {

    private lateinit var binding: FragmentAngeboteNewBinding
    private val basketViewModel: BasketViewModel by activityViewModels()
    private val viewModel: AngeboteViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_angebote_new, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Log.d("Hallo",basketViewModel.getBasket().toString())
        val dataset = viewModel.getData(basketViewModel.getBasket())
        binding.angeboteNew.adapter =
            AngeboteAdapter(requireContext(), dataset ) { partItem: ArtikelData,add:Boolean ->
               addOrRemoveFromBasket(partItem,add)
            }


    }
    fun addOrRemoveFromBasket(artikelData: ArtikelData,add:Boolean){
        if (add){
            basketViewModel.addBasket(artikelData)
        }else{
            basketViewModel.removeBasket(artikelData)
        }
    }
}






