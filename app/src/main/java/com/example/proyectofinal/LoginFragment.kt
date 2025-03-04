package com.example.proyectofinal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginFragment: Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

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

            val user = binding.inputEmail.text.toString().trim()
            val password = binding.inputPassword.text.toString().trim()

            if (user.isEmpty())
            {
                binding.textInputLayout4.error = getString(R.string.errorEmail)
            }else if (password.isEmpty())
            {
                binding.textInputLayout.error = getString(R.string.errorPassword)
            }else
            {

                loginUser(user, password)
                //findNavController().navigate(R.id.action_firstFragment_to_scaffold)
            }

        }


    }
        private fun loginUser(email: String, password: String) {

            auth = FirebaseAuth.getInstance()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful)
                    {
                        Toast.makeText(requireContext(), "Sesión iniciada", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_firstFragment_to_scaffold)
                    } else {
                        Toast.makeText(requireContext(), "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                }
        }
}