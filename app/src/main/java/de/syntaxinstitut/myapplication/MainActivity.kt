package de.syntaxinstitut.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import de.syntaxinstitut.myapplication.databinding.ActivityMainBinding

/**
 * Main Activity, dient als Einstiegspunkt für die App
 */
class MainActivity : AppCompatActivity() {

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
        navController = findNavController(R.id.main_fragment)
//        NavigationUI.setupActionBarWithNavController(this, navController)

        // Richtet die Navigation Bar ein, sodass sie mit dem Nav Controller verknüpft ist
        navView.setupWithNavController(navController)

        navView.setOnItemReselectedListener {
            Log.e("Was geht", "Failed to")
        }
    }

    /**
     * Diese Funktion sorgt dafür, dass man über den zurück Button in der Actionbar zurück kommt
     */
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }


}
