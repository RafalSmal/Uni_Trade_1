package de.syntaxinstitut.myapplication.ui.auftrag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.adapter.AuftragAdapter
import de.syntaxinstitut.myapplication.databinding.FragmentAuftragBinding

/**
 * Diese kümmert sich um die Erstellten Aufträge und zeigt die
 * Daten / gekaufen Artikel der Kunden an
 */
class AuftragFragment : Fragment() {

    /* ---------- Globale Variablen ---------- */

    private lateinit var binding: FragmentAuftragBinding
    private lateinit var auftragViewModel: AuftragViewModel

    /* ---------- Lifecycle ---------- */


    /**
     * Diese Funktiuon wird zu beginn des Lifecycles eingefügt
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auftragViewModel = ViewModelProvider(requireActivity())[AuftragViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_auftrag, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.auftragRecycler.adapter =
            AuftragAdapter(auftragViewModel.getAllOrders(),requireContext())

        binding.auftragRecycler.layoutManager = LinearLayoutManager(requireContext())
    }


}