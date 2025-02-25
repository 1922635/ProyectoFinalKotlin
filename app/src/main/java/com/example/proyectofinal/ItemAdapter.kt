package com.example.proyectofinal

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.databinding.ItemLayoutBinding

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
            binding.iv.setImageResource(data.img)

            if (data.fav)
            {
                binding.fabFav.setImageResource (R.drawable.fav_selected)
            }else
            {
                binding.fabFav.setImageResource(R.drawable.fav_unselected)
            }

            binding.fabFav.setOnClickListener{
                if (data.fav)
                {
                    binding.fabFav.setImageResource (R.drawable.fav_unselected)
                }else
                {
                    binding.fabFav.setImageResource(R.drawable.fav_selected)
                }
                data.fav = !data.fav
            }
        }
    }
}