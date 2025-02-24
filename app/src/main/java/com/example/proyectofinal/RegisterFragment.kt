package com.example.proyectofinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.databinding.FragmentLoginBinding
import com.example.proyectofinal.databinding.FragmentRegisterBinding

class RegisterFragment: Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        binding.button3.setOnClickListener {
            findNavController()
                .navigate(R.id.action_secondFragment_to_firstFragment)
        }

    }

    companion object {

    }
}