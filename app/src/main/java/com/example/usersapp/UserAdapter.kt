package com.example.usersapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.usersapp.databinding.ItemUserBinding

class UserAdapter (
            private val users : List<User>
        ) : RecyclerView.Adapter<UserAdapter.ViewHolder>(){


    //ViewHolder
    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val binding = ItemUserBinding.bind(view)  //vinculamos la vista al adaptador
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)  //infla la vista de la plantilla
        return ViewHolder(view)  //devuelve cada elemento que pinta
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users.get(position)
        with(holder){  //vinculamos los datos de nuestro objeto con los elementos de la vista plantilla
            binding.tvOrder.text = user.id.toString()
            binding.tvName.text = user.name
        }}

    override fun getItemCount(): Int { //devuelve el tamaño de la lista para que el recycler sepa cuando no hay mas elementos que mostrar
        return users.size
    }
}