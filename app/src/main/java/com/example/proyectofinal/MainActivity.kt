package com.example.proyectofinal

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        

    }

    //Nuevo metodo para configurar el componente Navigation
    override fun onSupportNavigateUp(): Boolean
    {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                    as NavHostFragment


        val navController = navHostFragment.navController


        return navController.navigateUp() ||
                super.onSupportNavigateUp()


    }

    private fun cargarFragment(fragment: Fragment)
    {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()
    }

    //SearchView
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
}