package com.example.proyectofinal

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.example.proyectofinal.databinding.FragmentTabsBinding
import com.google.android.material.tabs.TabLayoutMediator

class TabsFragment: Fragment()
{
    private lateinit var binding: FragmentTabsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = FragmentTabsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter


        TabLayoutMediator(binding.tabLayout, binding.viewPager)
        {

                tab, position ->    tab.text = when (position)
        {
            0 -> getString(R.string.lista)
            1 -> getString(R.string.favoritos)
            else -> ""
        }


        }.attach()

    }


}