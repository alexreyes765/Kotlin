package com.itsrfid.cursokotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.itsrfid.cursokotlin.calculadoraimc.CalculadoraImcActivity
import com.itsrfid.cursokotlin.firstapp.FirstAppActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //inicializamos la pantalla inicial
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        splashScreen.setKeepOnScreenCondition{false}
        //Elementos
        val btnSaludar : Button = findViewById(R.id.btn_saludapp)
        val btnImc : Button = findViewById(R.id.btnImc)

        //Eventos de click
        btnSaludar.setOnClickListener { navigateSaludapp() }
        btnImc.setOnClickListener { navigateImc() }
    }


    private fun navigateImc(){
        val intent = Intent(this, CalculadoraImcActivity::class.java)
        startActivity(intent)
    }

    private fun navigateSaludapp() {
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }
}