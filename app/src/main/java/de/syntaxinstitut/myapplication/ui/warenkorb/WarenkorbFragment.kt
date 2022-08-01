package de.syntaxinstitut.myapplication.ui.warenkorb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.adapter.WarenkorbAdapter
import de.syntaxinstitut.myapplication.databinding.FragmentWarenkorbBinding
import de.syntaxinstitut.myapplication.util.BasketViewModel

class WarenkorbFragment : Fragment() {
    private lateinit var binding: FragmentWarenkorbBinding
    private val basketViewModel : BasketViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        binding.warenkorbRecycler.adapter = WarenkorbAdapter(requireContext(),basketViewModel.getBasket())
        binding.warenkorbRecycler.layoutManager = LinearLayoutManager(requireContext())
    }
}


