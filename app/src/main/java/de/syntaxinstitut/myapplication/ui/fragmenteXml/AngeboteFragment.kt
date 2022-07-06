package de.syntaxinstitut.myapplication.ui.fragmenteXml

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.adapter.AngeboteAdapter
import de.syntaxinstitut.myapplication.data.DataSource
import de.syntaxinstitut.myapplication.databinding.FragmentAngeboteNewBinding


class AngeboteFragment : Fragment() {

    private lateinit var binding: FragmentAngeboteNewBinding
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
        val dataset = DataSource().loadArtikel()
        binding.angeboteNew.adapter = AngeboteAdapter(requireContext(), dataset)


    }

}

