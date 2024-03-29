package de.syntaxinstitut.myapplication.ui.angebote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.adapter.AngeboteAdapter
import de.syntaxinstitut.myapplication.databinding.FragmentAngeboteNewBinding
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.util.BasketViewModel
import java.util.*


class AngeboteFragment : Fragment() {

    private lateinit var binding: FragmentAngeboteNewBinding
   // private val basketViewModel: BasketViewModel by activityViewModels()
    private lateinit var basketViewModel : BasketViewModel
    private val viewModel: AngeboteViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        basketViewModel = ViewModelProvider(requireActivity()) [BasketViewModel::class.java]

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

        // Der SnapHelper sorgt dafür,
        // dass die RecyclerView immer auf das aktuelle List Item springt
        val helper: SnapHelper = PagerSnapHelper()
        helper.attachToRecyclerView(binding.angeboteNew)

        //Log.d("Hallo",basketViewModel.getBasket().toString())
       // val dataset = viewModel.getData(basketViewModel.getBasket())

        viewModel.getData(basketViewModel.basket.value!!.toList())

        viewModel.angeboteChanged.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer {
                binding.angeboteNew.adapter =
                    AngeboteAdapter(requireContext(),it){ partItem : ArtikelData, add: Boolean ->
                        addOrRemoveFromBasket(partItem, add)
                    }
            }
        )
    }


//        binding.angeboteNew.adapter =
//            AngeboteAdapter(requireContext(), dataset ) { partItem: ArtikelData,add:Boolean ->
//               addOrRemoveFromBasket(partItem,add)
//            }
//
//
//    }
    fun addOrRemoveFromBasket(artikelData: ArtikelData,add:Boolean){
        if (add){
            basketViewModel.addBasket(artikelData)
        }else{
            basketViewModel.removeBasket(artikelData)
        }
    }
}






