package com.example.usersapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.usersapp.databinding.ItemUserBinding

class UserAdapter(
    private val context: Context,
    private val users: List<User>,
    private val listener: (User, Int) -> Unit  //TODO
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)  //infla la vista de la plantilla

        return ViewHolder(binding)  //devuelve cada elemento que pinta
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {  //Este metodo actua casi como un for recorriendo todas las posiciones de la lista
        val user = users[position]
        holder.bind(user, position)


        holder.itemView.setOnClickListener { //  asignamos evento de click
            listener(user, position)
        }
    }

    override fun getItemCount(): Int { //  devuelve el tamaño de la lista para que el recycler sepa cuando no hay mas elementos que mostrar
        return users.size
    }


    //ViewHolder
    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) { //vinculamos la vista al adaptador


        fun bind(user: User, position: Int) {
            with(binding) {  //  vinculamos los datos de nuestro objeto con los elementos de la vista plantilla
                ( position + 1 ).toString().also { tvOrder.text = it }       //  tvOrder.text = ( position + 1 ).toString()
                (user.name + " " + user.lastname).also { tvName.text = it }  //  tvName.text = user.name + " " + user.lastname
                Glide.with(context)
                    .load(user.url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(binding.ivImagen)
            }
        }


    }

}