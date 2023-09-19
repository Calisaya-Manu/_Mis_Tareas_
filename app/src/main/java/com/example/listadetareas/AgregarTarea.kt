package com.example.listadetareas

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AgregarTarea : AppCompatActivity() {
    lateinit var Aguardar:Button
    lateinit var Asalir:Button
    lateinit var titulo:EditText
    lateinit var Des:EditText
    var listaU="nn"
    lateinit var claveRecibida:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_tarea)
        val intent=intent
        if (intent.hasExtra("clave")) {
            claveRecibida = intent.getStringExtra("clave").toString()
        }
        inicializarvariables()
    }



    private fun inicializarvariables() {
        titulo=findViewById(R.id.titIng)
        Des=findViewById(R.id.desIng)
        Aguardar=findViewById(R.id.Aguardar)
        Aguardar.setOnClickListener {
            bajarnotas()
            this.finish()
        }
        Asalir=findViewById(R.id.Asalir)
        Asalir.setOnClickListener { this.finish() }
    }

    private fun bajarnotas() {
        val sharedPreferences = this.getSharedPreferences("Notas", Context.MODE_PRIVATE)
        if (sharedPreferences.contains(claveRecibida)) {
        //existe la clave
            val listaUN=sharedPreferences.getString(claveRecibida,null)
            val gson=Gson()
            val type=object : TypeToken<MutableList<Array<String>>>(){}.type
            val listaRecu:MutableList<Array<String>> = gson.fromJson(listaUN,type)
            guardarNota(listaRecu)

        }
        else{
            //no existe la clave
            val listan:MutableList<Array<String>> = mutableListOf()
            guardarNota(listan)
        }
    }

    private fun guardarNota(listaRecu: MutableList<Array<String>>) {
        val nuevan= arrayOf(titulo.text.toString(),Des.text.toString())
        listaRecu.add(nuevan)
        gpersistente(listaRecu)
    }

    private fun gpersistente(listaRecu: MutableList<Array<String>>) {
        val gson=Gson()
        val listaNotasJson= gson.toJson(listaRecu)
        val sharedPreferences:SharedPreferences=this.getSharedPreferences("Notas",Context.MODE_PRIVATE)
        val edit=sharedPreferences.edit()
        edit.putString(claveRecibida,listaNotasJson)
        edit.apply()
        Toast.makeText(this,"CARGADO",Toast.LENGTH_SHORT).show()
    }
}