package com.example.proyectofinal

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.proyectofinal.databinding.FragmentContactoBinding

class ContactoFragment: Fragment() {
    private lateinit var binding: FragmentContactoBinding

    private val CALL_PHONE_PERMISSION_REQUEST = 123

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentContactoBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        binding.button5.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    Activity(),
                    android.Manifest.permission.CALL_PHONE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                hacerLlamada()
            } else {
                ActivityCompat.requestPermissions(
                    Activity(),
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    CALL_PHONE_PERMISSION_REQUEST
                )
            }
        }

        binding.button6.setOnClickListener {
            mandarEmail()
        }

        binding.button7.setOnClickListener {
            abrirWhatsApp()
        }

        binding.button8.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    Activity(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                abrirMapa()
            } else {
                ActivityCompat.requestPermissions(
                    Activity(),
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    CALL_PHONE_PERMISSION_REQUEST
                )
            }


            abrirMapa()

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CALL_PHONE_PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                hacerLlamada()
            }
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun hacerLlamada() {
        /*val phoneNumber = "1234567890"

        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber"))

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }*/
    }

    private fun mandarEmail() {
        val email = "1922635@alu.murciaeduca.es"
        val subject = "Login Email PMDM"
        val body = "Si estás leyendo esto, la app funciona correctamente"

        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
        }

        startActivity(intent)
    }

    private fun abrirWhatsApp() {
        val url = "https://api.whatsapp.com/send?phone=+34644611678"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        intent.setPackage("com.whatsapp")
        startActivity(intent)
    }

    private fun abrirMapa() {
        val latitude = "37.605811"
        val longitude = "-0.990161"
        val zoom = "19"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$latitude,$longitude?z=$zoom"))
        startActivity(intent)
    }

}