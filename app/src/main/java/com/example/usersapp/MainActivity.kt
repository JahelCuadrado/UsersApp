package com.example.usersapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usersapp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    private lateinit var userAdapter         : UserAdapter
    private lateinit var linearLayoutManager : RecyclerView.LayoutManager
    private lateinit var binding             : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecycler()
        sharedPreferences()

    }


    //TODO
    private fun sharedPreferences(){
        val preferences = getPreferences(Context.MODE_PRIVATE)  //declaramos el modo de nuestro getpreference
        val isFirstTime = preferences.getBoolean("first_time", true)  //recuperamos de las preferencias un valor booleano
        // llamado first_name, este valor no existe en las prefencias, porque nos lo hemos inventado, asique devolverá el valor por defecto asignado



        if (isFirstTime){
            val dialogView = layoutInflater.inflate(R.layout.dialog_register, null)
            MaterialAlertDialogBuilder(this)  //cuadro de dialogo personalizado
                .setTitle("Bienvenido!!")
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton("Registrar"){ _, _ ->

                    val username = dialogView.findViewById<TextInputEditText>(R.id.et_username).text.toString()  //guardamos en una variable el texto introducido

                    with(preferences.edit()){
                        putBoolean("first_time", false   ) //guardamos en la preference el valor false en first_time
                        putString ("username"  , username) //guardamos en la preference el valor intrudicido, con la clave username.
                            .apply()  //aplicar los cambios
                    }
                    Toast.makeText(this, "Gracias por registrarte $username", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancelar") { _, _ ->
                    with(preferences.edit()){
                        putBoolean("first_time", true)
                            .apply()  //aplicar los cambios
                    }
                }
                .show()
        }else{
            val username = preferences.getString("username", "Nose")
            Toast.makeText(this, "Bienvenido $username", Toast.LENGTH_SHORT).show()
        }
    }


    private fun initRecycler(){
        userAdapter = UserAdapter(this, getUsers(), ::eventClicRecycler)
        linearLayoutManager = LinearLayoutManager(this)

        binding.reciclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
            adapter = userAdapter
        }
    }


    private fun eventClicRecycler(user: User, position: Int){
        Toast.makeText(this, "${position+1}: " + user.name, Toast.LENGTH_SHORT).show()
    }


    private fun getUsers() : MutableList<User>{
        val users = mutableListOf<User>()

        val alain   = User (1, "Alain",   "Nicolás", "https://frogames.es/wp-content/uploads/2020/09/alain-1.jpg"                         )
        val samanta = User (2, "Samanta", "Meza",    "https://upload.wikimedia.org/wikipedia/commons/b/b2/Samanta_villar.jpg"             )
        val javier  = User (3, "Javier",  "Gómez",   "https://live.staticflickr.com/974/42098804942_b9ce35b1c8_b.jpg"                     )
        val emma    = User (4, "Emma",    "Cruz",    "https://upload.wikimedia.org/wikipedia/commons/d/d9/Emma_Wortelboer_%282018%29.jpg" )

        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)
        users.add(alain)
        users.add(samanta)
        users.add(javier)
        users.add(emma)

        return users
    }

}