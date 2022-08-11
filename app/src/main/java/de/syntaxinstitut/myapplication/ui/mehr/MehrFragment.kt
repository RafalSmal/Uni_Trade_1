package de.syntaxinstitut.myapplication.ui.mehr

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import de.syntaxinstitut.myapplication.R
import de.syntaxinstitut.myapplication.databinding.FragmentMehrBinding
import de.syntaxinstitut.myapplication.ui.auftrag.AuftragViewModel

private lateinit var binding: FragmentMehrBinding

/**
 * Dieses Fragment ist dafür Zuständig, einen Kontakt anzulegen
 */
class MehrFragment : Fragment() {

    /* ---------- Globale Variablen ---------- */

    private val viewModel: AuftragViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mehr, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return

        binding.editTextFirmaMore.setText(sharedPref.getString(getString(R.string.firma),""))
        binding.editTextTextStreetMore.setText(sharedPref.getString(getString(R.string.street),""))
        binding.editTextStadtMore.setText(sharedPref.getString(getString(R.string.stadt),""))
        binding.editTextLieferadresseMore.setText(sharedPref.getString(getString(R.string.adresse),""))
        binding.editTextTeleMore.setText(sharedPref.getString(getString(R.string.telefon),""))
        binding.editTextEMailMore.setText(sharedPref.getString(getString(R.string.mail),""))

         binding.addCustomerMore.setOnClickListener {
            with(sharedPref.edit()) {
                putString(getString(R.string.firma), binding.editTextFirmaMore.text.toString())
                apply()
            }

            with(sharedPref.edit()) {
                putString(
                    getString(R.string.street),
                    binding.editTextTextStreetMore.text.toString()
                )
                apply()
            }

            with(sharedPref.edit()) {
                putString(getString(R.string.stadt), binding.editTextStadtMore.text.toString())
                apply()
            }

            with(sharedPref.edit()) {
                putString(
                    getString(R.string.adresse),
                    binding.editTextLieferadresseMore.text.toString()
                )
                apply()
            }

            with(sharedPref.edit()) {
                putString(getString(R.string.telefon), binding.editTextTeleMore.text.toString())
                apply()
            }

            with(sharedPref.edit()) {
                putString(getString(R.string.mail), binding.editTextEMailMore.text.toString())
                apply()
            }
             Toast.makeText(requireContext(), "Daten wurden gespeichert.", Toast.LENGTH_SHORT)
                 .show()
        }
    }
}