package com.example.proyectofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.databinding.FragmentScaffoldBinding

class ScaffoldFragment : Fragment()
{
    private lateinit var binding: FragmentScaffoldBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        // Inflate the layout for this fragment
        binding = FragmentScaffoldBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {

        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController


        /* DRAWER LAYOUT */
        val toggle = ActionBarDrawerToggle(
            requireActivity(), binding.drawerLayout, binding.toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationView.setNavigationItemSelectedListener {

            item -> when(item.itemId)
            {
                R.id.nav_home -> {
                    navController.navigate(R.id.FragmentContacto)
                    true
                }

                R.id.nav_dashboard -> {
                    navController.navigate(R.id.FragmentLista)
                    true
                }

                R.id.nav_notifications -> {
                    navController.navigate(R.id.FragmentTabs)
                    true
                }

                R.id.nav_logOut -> {
                    findNavController()
                        .navigate(R.id.action_scaffold_to_firstFragment)
                    true
                }
                else -> false
            }
        }

        /* TOOLBAR */
        /* Establece la Toolbar como nueva ActionBar */
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object: MenuProvider {

            /* Infla la vista del menú */
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater)
            {
                menuInflater.inflate(R.menu.toolbar, menu)
            }

            /* Gestiona evento onClick */
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_search -> {
                        // Manejar la selección del item1
                        true
                    }
                    R.id.action_sort -> {
                        // Manejar la selección del item2
                        true
                    }
                    R.id.action_settings -> {
                        // Manejar la selección del item3
                        true
                    }
                    R.id.logOut -> {
                        findNavController()
                            .navigate(R.id.action_scaffold_to_firstFragment)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        /* BOTTOM NAVIGATION MENU */
        binding.bottomNavigation.setOnItemSelectedListener {
                item ->
            when (item.itemId) {
                R.id.bnm_home -> {
                    navController.navigate(R.id.FragmentContacto)
                    true
                }
                R.id.bnm_dashboard -> {
                    navController.navigate(R.id.FragmentLista)
                    true
                }
                R.id.bnm_notifications -> {
                    navController.navigate(R.id.FragmentTabs)
                    true
                }
                else -> false
            }
        }
    }
}