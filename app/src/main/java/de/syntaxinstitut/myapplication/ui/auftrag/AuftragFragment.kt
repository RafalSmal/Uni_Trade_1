package de.syntaxinstitut.myapplication.ui.auftrag

import android.content.Context
import android.os.Bundle
import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.adapter.AuftragAdapter
import de.syntaxinstitut.myapplication.databinding.FragmentAuftragBinding
import java.util.zip.Inflater

class AuftragFragment : Fragment() {
    private lateinit var binding: FragmentAuftragBinding
    private lateinit var auftragViewModel: AuftragViewModel

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

        val allOrder = auftragViewModel.getAllOrders()


        binding.auftragRecycler.adapter =
            AuftragAdapter(auftragViewModel.getAllOrders(),requireContext())

        binding.auftragRecycler.layoutManager = LinearLayoutManager(requireContext())
    }


}