package com.example.proyectofinal

import android.app.Activity
import android.net.http.HttpException
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.databinding.FragmentListaBinding
import com.example.proyectofinal.databinding.FragmentLoginBinding
import com.google.api.Distribution
import com.google.firebase.ktx.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import kotlinx.coroutines.*
import com.example.proyectofinal.Item
import com.example.proyectofinal.ItemAdapter

class ListaFragment: Fragment()
{
    private lateinit var binding: FragmentListaBinding
    private var piezas: MutableList<Item> = mutableListOf()
    private lateinit var adapter: ItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListaBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.swipeRefresh.setOnRefreshListener {
            CoroutineScope(Dispatchers.IO).launch {
                try
                {
                    async { cargarPiezas() }.await()
                }catch (e: HttpException)
                {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }catch (e: Exception)
                {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }finally {
                    withContext(Dispatchers.Main)
                    {
                        adapter = ItemAdapter(requireActivity(), piezas)
                        binding.rv.adapter = adapter
                        binding.swipeRefresh.isRefreshing = false
                    }
                }
            }
        }



        /*val piezas = listOf(
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
        recyclerView.adapter = ItemAdapter(Activity(), piezas)*/
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onResume()
    {
        super.onResume()

        CoroutineScope(Dispatchers.IO).launch {
            try
            {
                async { cargarPiezas() }.await()
            }catch (e: HttpException)
            {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }catch (e: Exception)
            {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }finally {
                withContext(Dispatchers.Main) {
                    adapter = ItemAdapter(requireActivity(), piezas)
                    binding.rv.adapter = adapter
                    binding.swipeRefresh.isRefreshing = false
                }
            }
        }
    }

    private suspend fun cargarPiezas()
    {
        //FirebaseApp.initializeApp(requireActivity())
        val db = Firebase.firestore

        piezas.clear()

        withContext(Dispatchers.Main)
        {
            binding.rv.visibility = View.GONE
            binding.progressBar.visibility = ProgressBar.VISIBLE
        }

        db.collection("items").get().addOnSuccessListener { result ->
            for (document in result) {
                val pieza = Item(
                    document.id.toInt(),
                    document.get("nombre") as String,
                    document.get("valor") as String,
                    document.get("fortaleza") as String,
                    document.get("debilidad") as String,
                    document.get("fav") as Boolean
                )
                piezas.add(pieza)
            }
        }
            .addOnFailureListener {
                //Toast.makeText(Activity(), "Error al cargar las piezas", Toast.LENGTH_SHORT).show()
            }

        for (i in 1..100) {
            delay(10) // Simula una tarea larga
            binding.progressBar.progress = i
        }

        withContext(Dispatchers.Main)
        {
            binding.progressBar.visibility = ProgressBar.GONE
            binding.rv.visibility = View.VISIBLE
        }
    }
}