package com.example.listadetareas

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Iniciar_Sesion : AppCompatActivity() {

    lateinit var usuarioing:EditText
    lateinit var contraing:EditText

    lateinit var bacepta:Button
    lateinit var bvolver:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)
        instanciarVariables()
    }

    private fun instanciarVariables() {
        usuarioing=findViewById(R.id.usuI)
        contraing=findViewById(R.id.contraI)

        bacepta=findViewById(R.id.Iaceptar)
        bacepta.setOnClickListener {
            CambiarColor(bacepta)
            validarDatos()
        }

        bvolver=findViewById(R.id.Ivolve)

        bvolver.setOnClickListener {
            CambiarColor(bvolver)
            this.finish()
        }

    }
    private fun validarDatos() {
        val us=usuarioing.text.toString()
        val co=contraing.text.toString()

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
            var encontrado = false
            var contraBaja="n"
            //comparo si existe el usuario ingresado en la lista recuperada
            for (array in listaRecuperada) {
                if (array.isNotEmpty() && array[0] == us) {
                    contraBaja = array[1].toString()
                    encontrado = true
                    break // Si encontramos una coincidencia, podemos salir del bucle
                }
            }
            //si se encontro el usuario en la lista
            if (encontrado) {
                compararComtraseña(contraBaja,co)
            } else {
                //como no existe guardo el usuario en la lista
                Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "no hay usuarios registrados", Toast.LENGTH_SHORT).show()

        }
}

    private fun compararComtraseña(cb:String ,co:String) {
        if (co==cb){
            Toast.makeText(this, "SESSION INICIADA", Toast.LENGTH_SHORT).show()
            var i = Intent(this, MisNotas::class.java)
            var u=usuarioing.text.toString()+contraing.text.toString()
            i.putExtra("clave",u)
            startActivity(i)
        }
        else{
            Toast.makeText(this, "contraseña incorrecta", Toast.LENGTH_SHORT).show()
        }
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