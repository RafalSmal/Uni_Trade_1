package de.syntaxinstitut.myapplication

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import de.syntaxinstitut.myapplication.adapter.AngeboteAdapter
import de.syntaxinstitut.myapplication.databinding.ActivityMainBinding
import de.syntaxinstitut.myapplication.datamodels.ArtikelData
import de.syntaxinstitut.myapplication.ui.angebote.AngeboteViewModel

/**
 * Main Activity, dient als Einstiegspunkt für die App
 */
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    /* -------------------- Klassen Variablen -------------------- */

    /** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController


    /* -------------------- Lifecycle -------------------- */

    /**
     * Lifecycle Methode, wird aufgerufen wenn Activity erstellt wird
     *
     * @param savedInstanceState      Save state vom view
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Das Binding zur XML-Datei
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Die Navigationsleiste am unteren Bildschirmrand wird eingerichtet
        val navView: BottomNavigationView = binding.BottomNavigationView

        // Hier wird der Nav Controller zugewiesen und die Action Bar damit eingerichtet
        navController = this.findNavController(R.id.main_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        // Richtet die Navigation Bar ein, sodass sie mit dem Nav Controller verknüpft ist
        NavigationUI.setupWithNavController(navView,navController)
        navView.setupWithNavController(navController)


        // always show selected Bottom Navigation item as selected (return true)
        navView.setOnItemSelectedListener { item ->

            NavigationUI.onNavDestinationSelected(item, navController)

            val graph = navController.graph.findNode(item.itemId) as? NavGraph?

            graph?.startDestinationId?.let {
                navController.popBackStack(it, false)
            }

            return@setOnItemSelectedListener true
        }

                mainViewModel.loadingScreen.observe(
            this,
            androidx.lifecycle.Observer {
                binding.BottomNavigationView.isVisible = !it
            }
        )



        supportActionBar?.hide()
    }


    /**
     * Diese Funktion sorgt dafür, dass man über den zurück Button in der Actionbar zurück kommt
     */
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }



}
