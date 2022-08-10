package de.syntaxinstitut.myapplication.ui.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.databinding.FragmentHomescreenBinding
import de.syntaxinstitut.myapplication.ui.BasketViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomescreenFragment : Fragment() {
    private lateinit var binding: FragmentHomescreenBinding
    private val viewModel: BasketViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_homescreen, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       viewModel.showLoadingScreen()


        lifecycleScope.launch() {
            delay(3000)
            viewModel.hideLoadingScreen()
            findNavController().navigate(HomescreenFragmentDirections.actionHomescreenFragmentToSatteRabatteFragment())

        }
    }


}

