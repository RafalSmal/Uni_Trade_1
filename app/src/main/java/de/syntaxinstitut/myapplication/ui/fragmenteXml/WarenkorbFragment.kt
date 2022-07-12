package de.syntaxinstitut.myapplication.ui.fragmenteXml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.adapter.WarenkorbAdapter
import de.syntaxinstitut.myapplication.data.DataSource
import de.syntaxinstitut.myapplication.databinding.FragmentKategorienBinding
import de.syntaxinstitut.myapplication.databinding.FragmentWarenkorbBinding

class WarenkorbFragment : Fragment() {
    private lateinit var binding: FragmentWarenkorbBinding
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
        val dataset = DataSource().loadArtikel()
        binding.warenkorbRecycler.adapter = WarenkorbAdapter(requireContext(), dataset)
    }
}


