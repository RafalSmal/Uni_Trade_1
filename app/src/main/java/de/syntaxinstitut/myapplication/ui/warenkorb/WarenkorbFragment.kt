package de.syntaxinstitut.myapplication.ui.warenkorb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.adapter.WarenkorbAdapter
import de.syntaxinstitut.myapplication.databinding.FragmentWarenkorbBinding
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.util.BasketViewModel

class WarenkorbFragment : Fragment() {
    private lateinit var binding: FragmentWarenkorbBinding
    private lateinit var basketViewModel : BasketViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        basketViewModel = ViewModelProvider(requireActivity()) [BasketViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_warenkorb, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.warenkorbRecycler.adapter = WarenkorbAdapter(requireContext()){partItem : ArtikelData, add: Boolean ->
            addOrRemoveFromBasket(partItem,add)
        }
        binding.warenkorbRecycler.layoutManager = LinearLayoutManager(requireContext())

        basketViewModel.basket.observe(viewLifecycleOwner){
            (binding.warenkorbRecycler.adapter as WarenkorbAdapter).update(it)
        }
        gesamtPreisWk()

        binding.buyBtnWk.setOnClickListener{
            basketViewModel.warenkorbEnde()
            basketViewModel.basket.value!!.clear()
            (binding.warenkorbRecycler.adapter as WarenkorbAdapter).update(basketViewModel.basket.value!!)
        }
    }



    fun addOrRemoveFromBasket(artikelData: ArtikelData, add:Boolean){
        if (add){
            basketViewModel.addBasket(artikelData)
        }else{
            basketViewModel.removeBasket(artikelData)
        }

        (binding.warenkorbRecycler.adapter as WarenkorbAdapter).update(basketViewModel.basket.value!!.toList())

        gesamtPreisWk()
    }

    fun gesamtPreisWk (){
        var gesamtPreis = 0.0

        for (item in basketViewModel.basket.value!!){
            gesamtPreis += (item.quantity * item.price!!)
        }
        binding.totalPriceWk.text = "%.2f €".format(gesamtPreis)
    }
}


