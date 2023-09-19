package com.example.listadetareas

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    //variables
    lateinit var Biniciar: Button
    lateinit var Bcrear: Button
    lateinit var Bsalir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        instanciar_Variables()
    }

    //instancia las variables
    private fun instanciar_Variables() {
        Biniciar = findViewById(R.id.BiniciarS)
        Biniciar.setOnClickListener { PresionoIniciar(Biniciar) }

        Bcrear = findViewById(R.id.BcrearC)
        Bcrear.setOnClickListener { PresionoCrear(Bcrear) }

        Bsalir = findViewById(R.id.Bsalir)
        Bsalir.setOnClickListener { PresionoSalir(Bsalir) }

    }

    //Si se presiona el boton iniciar session realiza esto
    private fun PresionoIniciar(Biniciar: Button) {
        CambiarColor(Biniciar)//cambia de color al presionarse el boton
        var i = Intent(this, Iniciar_Sesion::class.java)
        startActivity(i)

    }

    //si se presiona el boton crear cuenta realiza esto
    private fun PresionoCrear(Bcrear: Button) {
        CambiarColor(Bcrear)//cambia de color al presionarse el boton
        var i = Intent(this, Crear_Cuenta::class.java)
        startActivity(i)
    }

    //si se presiona el boton salir
    private fun PresionoSalir(Bsalir: Button) {
        CambiarColor(Bsalir)//cambia de color al presionarse el boton
        this.finish()
    }

    //implementa como se cambia de color y vuelve a l original
    private fun CambiarColor(boton: Button) {
        //cambia el color
        boton.setBackgroundColor(Color.parseColor("#FF4CAF50"))
        //restaura al color original
        Handler().postDelayed({
            boton.setBackgroundColor(Color.parseColor("#ffffff"))
        }, 500)
    }
}