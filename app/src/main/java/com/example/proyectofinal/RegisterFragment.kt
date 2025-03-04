package com.example.proyectofinal

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.proyectofinal.databinding.FragmentLoginBinding
import com.example.proyectofinal.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class RegisterFragment: Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(R.drawable.person).into(binding.imageView)

        binding.button3.setOnClickListener {

            val calendario = Calendar.getInstance()
            val year = calendario.get(Calendar.YEAR)
            val month = calendario.get(Calendar.MONTH)
            val day = calendario.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                val fecha = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.button3.text = fecha
            }, year, month, day)

            datePickerDialog.show()
        }

        binding.button4.setOnClickListener {

            val user = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()

            if (user.isEmpty())
            {
                binding.textInputLayout2.error = getString(R.string.errorEmail)
            }else if (password.isEmpty())
            {
                binding.textInputLayout3.error = getString(R.string.errorPassword)
            }else
            {
                findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
            }
        }
    }

    companion object {

    }
}