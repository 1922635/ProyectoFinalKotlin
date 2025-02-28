package com.example.proyectofinal

data class Item (val id:Int, val nombre:String, val valor:String, val fortaleza:String, val debilidad:String, var fav:Boolean)
{
    var favorito: Boolean = fav
}