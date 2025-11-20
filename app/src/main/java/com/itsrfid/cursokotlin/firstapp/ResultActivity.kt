package com.itsrfid.cursokotlin.firstapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.itsrfid.cursokotlin.R


class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //elementos del xml
        val imagen : ImageView = findViewById(R.id.gif_maldito_alcohol)
        val url = R.drawable.erizo
        val txtSaludo = findViewById<TextView>(R.id.txt_saludo)
        val btnBack: Button = findViewById(R.id.back)
        val name: String = intent.extras?.getString("Nombre_Extra").orEmpty()

        //asignaciones del xml
        txtSaludo.text = "Hola $name"
        //aqui se usa glide para poder mostrar el gif
        Glide.with(this)
            .asGif()
            .load(url)
            .override(400, 400)
            .into(imagen)

        btnBack.setOnClickListener { finish() }
    }

}