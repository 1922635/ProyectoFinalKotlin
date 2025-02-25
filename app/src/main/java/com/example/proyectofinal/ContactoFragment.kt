package com.example.proyectofinal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.databinding.FragmentContactoBinding

class ContactoFragment: Fragment() {
    private lateinit var binding: FragmentContactoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentContactoBinding.inflate(layoutInflater)
        return binding.root
    }
}