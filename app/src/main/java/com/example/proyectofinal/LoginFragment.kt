package com.example.proyectofinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar

    class LoginFragment: Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        binding.button2.setOnClickListener {
            findNavController()
                .navigate(R.id.action_firstFragment_to_secondFragment)
        }

        binding.button.setOnClickListener {
            findNavController()
                .navigate(R.id.action_firstFragment_to_scaffold)
        }

    }

    companion object {

    }
}