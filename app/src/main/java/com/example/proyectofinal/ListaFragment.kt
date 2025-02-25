package com.example.proyectofinal

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.databinding.FragmentListaBinding
import com.example.proyectofinal.databinding.FragmentLoginBinding

class ListaFragment: Fragment()
{
    private lateinit var binding: FragmentListaBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListaBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val piezas = listOf(
            Item(R.drawable.dama, "Dama", "Valor: 9", "Fortaleza: Versatilidad", "Debilidad: Sobrecarga", false),
            Item(R.drawable.peon, "Peón", "Valor: 1", "Fortaleza: Consistencia", "Debilidad: Monotonía", false),
            Item(R.drawable.caballo, "Caballo", "Valor: 3", "Fortaleza: Alcance", "Debilidad: Maniobrabilidad", false),
            Item(R.drawable.alfil, "Alfil", "Valor: 3", "Fortaleza: Precisión", "Debilidad: Defensa", false),
            Item(R.drawable.torre, "Torre", "Valor: 5", "Fortaleza: Presión", "Debilidad: Velocidad", false),
            Item(R.drawable.rey, "Rey", "Valor: Indefinido", "Fortaleza: Maniobrabilidad", "Debilidad: Vulnerabilidad", false)
        )

        val adapter = ItemAdapter(Activity(), piezas)

        val recyclerView: RecyclerView = binding.rv

        recyclerView.layoutManager = LinearLayoutManager(Activity())
        recyclerView.adapter = ItemAdapter(Activity(), piezas)
    }
}