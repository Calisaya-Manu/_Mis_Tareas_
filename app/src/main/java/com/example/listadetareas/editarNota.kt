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

class editarNota : AppCompatActivity() {
    lateinit var claveRecibida:String
    lateinit var posicion:String
    var listaRecu2:MutableList<Array<String>> = mutableListOf()
    lateinit var ed1:EditText
    lateinit var ed2:EditText
    lateinit var Bborrar:Button
    lateinit var Bguardar:Button
    lateinit var GTreali:Button
    lateinit var actividadOrigen:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_nota)
        val intent=intent
        if (intent.hasExtra("clave")){
            claveRecibida = intent.getStringExtra("clave").toString()
        }
        if (intent.hasExtra("pos")){
            posicion=intent.getStringExtra("pos").toString()
        }
        if (intent.hasExtra("ac")) {
            actividadOrigen = intent.getStringExtra("ac").toString()
            if(actividadOrigen=="r"){
                Bguardar=findViewById(R.id.Eguardar)
                GTreali=findViewById(R.id.ATrealizadas)
                //desactivo botones guardar y marcar como realizada solo dejo boton borrar
                Bguardar.isEnabled=false
                GTreali.isEnabled=false

            }
        }
        iniciar()
    }

    private fun iniciar() {
        ed1=findViewById(R.id.Etitulo1)
        ed2=findViewById(R.id.Edes1)
        bajarLista()
        Bborrar=findViewById(R.id.Eborrar)
        Bborrar.setOnClickListener { borrarTarea() }
        Bguardar=findViewById(R.id.Eguardar)
        Bguardar.setOnClickListener { guardarNotaEditada() }
        GTreali=findViewById(R.id.ATrealizadas)
        GTreali.setOnClickListener { 
            var array=listaRecu2[posicion.toInt()]
            listaRecu2.removeAt(posicion.toInt())
            guardarPersistente(listaRecu2)
            val intent = Intent(this, tareasRealizadas::class.java)
            intent.putExtra("realizada", array)
            intent.putExtra("clave",claveRecibida)
            startActivity(intent)
            this.finish()
        }
    }

    private fun guardarNotaEditada() {
        listaRecu2[posicion.toInt()][0]=ed1.text.toString()
        listaRecu2[posicion.toInt()][1]=ed2.text.toString()
        guardarPersistente(listaRecu2)
        this.finish()
    }

    private fun borrarTarea() {
        if (posicion.toInt() >= 0 && posicion.toInt() < listaRecu2.size) {
            listaRecu2.removeAt(posicion.toInt())
        }
        guardarPersistente(listaRecu2)
        this.finish()
    }

    private fun guardarPersistente(listaRecu2: MutableList<Array<String>>) {
        val gson=Gson()
        val listaNotasJson= gson.toJson(listaRecu2)

        val sharedPreferences:SharedPreferences
        if (actividadOrigen=="r"){
            sharedPreferences = this.getSharedPreferences("NotasR", Context.MODE_PRIVATE)

        }else{
            sharedPreferences = this.getSharedPreferences("Notas", Context.MODE_PRIVATE)
        }
        val edit=sharedPreferences.edit()
        edit.putString(claveRecibida,listaNotasJson)
        edit.apply()
    }

    private fun bajarLista() {
        val sharedPreferences:SharedPreferences
        if (actividadOrigen=="r"){
            sharedPreferences = this.getSharedPreferences("NotasR", Context.MODE_PRIVATE)

        }else{
             sharedPreferences = this.getSharedPreferences("Notas", Context.MODE_PRIVATE)
        }
        if (sharedPreferences.contains(claveRecibida)) {
            //existe la clave
            val listaUN=sharedPreferences.getString(claveRecibida,null)
            val gson= Gson()
            val type=object : TypeToken<MutableList<Array<String>>>(){}.type
            val  listaRecu:MutableList<Array<String>> = gson.fromJson(listaUN,type)
            listaRecu2=listaRecu
            mostrarnotas(listaRecu)
        }
    }

    private fun mostrarnotas(listaRecu: MutableList<Array<String>>) {
        var p=posicion.toInt()
        if (p != null && p >= 0 && p < listaRecu.size) {
            val array = listaRecu[p]
            ed1.setText(array[0].toString())
            ed2.setText(array[1].toString())
        }
    }
}

