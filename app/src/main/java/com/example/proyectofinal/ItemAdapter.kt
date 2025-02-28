package com.example.proyectofinal

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.databinding.ItemLayoutBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class ItemAdapter(private val context: Context, private val items: List<Item> ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): ItemViewHolder
    {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder (binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int
    {
        return items.size
    }

    class ItemViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(data: Item)
        {
            binding.nombre.text = data.nombre
            binding.valor.text = data.valor
            binding.debilidad.text = data.debilidad
            binding.fortaleza.text = data.fortaleza

            //Aquí pongo las imagenes manualmente en función del id de la pieza
            if (data.id == 1)
            {
                binding.iv.setImageResource(R.drawable.dama)
            }else if (data.id == 2)
            {
                binding.iv.setImageResource(R.drawable.peon)
            } else if (data.id == 3)
            {
                binding.iv.setImageResource(R.drawable.caballo)
            } else if (data.id == 4)
            {
                binding.iv.setImageResource(R.drawable.alfil)
            } else if (data.id == 5)
            {
                binding.iv.setImageResource(R.drawable.torre)
            } else if (data.id == 6)
            {
                binding.iv.setImageResource(R.drawable.rey)
            }else
            {
                binding.iv.setImageResource(R.drawable.peon)
            }



            if (data.favorito)
            {
                binding.fabFav.setImageResource (R.drawable.fav_selected)
            }else
            {
                binding.fabFav.setImageResource(R.drawable.fav_unselected)
            }

            val db = Firebase.firestore

            binding.fabFav.setOnClickListener{
                if (data.favorito)
                {
                    binding.fabFav.setImageResource (R.drawable.fav_unselected)
                    db.collection("items").document(data.id.toString()).update("fav", false)
                }else
                {
                    binding.fabFav.setImageResource(R.drawable.fav_selected)
                    db.collection("items").document(data.id.toString()).update("fav", true)
                }
                data.favorito = !data.favorito
            }

        }
    }
}