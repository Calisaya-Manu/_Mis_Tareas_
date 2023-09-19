package com.example.listadetareas

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Crear_Cuenta : AppCompatActivity() {
    //variables para guardar permanentemente los datos


    //variables de los datos
    lateinit var usuarioIngresado:EditText
    lateinit var contraIngresada1:EditText
    lateinit var contraIngresada2:EditText
    lateinit var botonAceptar:Button
    lateinit var botonSalir:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)
        instanciarVariables()

    }

    private fun instanciarVariables(){
        usuarioIngresado=findViewById(R.id.Escribe1)
        contraIngresada1=findViewById(R.id.Escribe2)
        contraIngresada2=findViewById(R.id.Escribe3)

        botonAceptar=findViewById(R.id.CBaceptar)
        botonAceptar.setOnClickListener { aceptarDatos(botonAceptar) }

        botonSalir=findViewById(R.id.CBsalir)
        botonSalir.setOnClickListener { salir(botonSalir) }

    }

    private fun aceptarDatos(b:Button){
        CambiarColor(b)//cambia de color al presionarse el boton
        verificarContraseñas()

    }

    private fun verificarContraseñas(){
        //verifico las contraseñas sean iguales
        if(contraIngresada1.text.toString()==contraIngresada2.text.toString()){
            //accedo a los usuarios
            bajarUsuarios()
        }//si no son iguales muestro mensaje
        else{
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
        }
    }

    private fun bajarUsuarios(){
        //bajo la clave maestro donde esta la lista
        val sharedPreferences = this.getSharedPreferences("Maestro", Context.MODE_PRIVATE)

        //verifico si la clave Usuario123 existe
        val clave = "Usuarios123"
        if (sharedPreferences.contains(clave)) {
            //si existe la  clave
            //me bajo la lista
            val listaU = sharedPreferences.getString("Usuarios123", null)
            val gson = Gson()
            val type = object : TypeToken<MutableList<Array<String>>>() {}.type
            val listaRecuperada: MutableList<Array<String>> = gson.fromJson(listaU, type)
            //guardo el usuario ingresado
            val uingreso=usuarioIngresado.text.toString()
            var encontrado = false
            //comparo si existe el usuario ingresado en la lista recuperada
            for (array in listaRecuperada) {
                if (array.isNotEmpty() && array[0] == uingreso) {
                    encontrado = true
                    break // Si encontramos una coincidencia, podemos salir del bucle
                }
            }
            //si se encontro el usuario en la lista
            if (encontrado) {
                //muestro que ya tengo el mismo usuario
                Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT).show()
            } else {
                //como no existe guardo el usuario en la lista
                guardarUsuario(listaRecuperada)
            }

        } else {
            //no existe la clave
            val listaUsuarios:MutableList<Array<String>> = mutableListOf()
            guardarUsuario(listaUsuarios)
        }
    }

    private fun guardarUsuario(l:MutableList<Array<String>> = mutableListOf()){
        //el usuario ingresado lo añado a la lista
        val nuevoUsuario= arrayOf(usuarioIngresado.text.toString(),contraIngresada1.text.toString())
        l.add(nuevoUsuario)
        guardarPersistente(l)
    }

    private fun guardarPersistente(l:MutableList<Array<String>> = mutableListOf()){
        //cargo la lista de vuelta al sharet preferencer
        val gson = Gson()
        val listaUsuariosJson = gson.toJson(l)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("Maestro", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("Usuarios123", listaUsuariosJson)
        editor.apply()
        Toast.makeText(this, "GUARDADO", Toast.LENGTH_SHORT).show()
    }
    private fun salir(b:Button){
        CambiarColor(b)//cambia de color al presionarse el boton
        this.finish()
    }


    private fun CambiarColor(boton: Button) {
        //cambia el color
        boton.setBackgroundColor(Color.parseColor("#FF4CAF50"))
        //restaura al color original
        Handler().postDelayed({
            boton.setBackgroundColor(Color.parseColor("#ffffff"))
        }, 500)
    }
}

