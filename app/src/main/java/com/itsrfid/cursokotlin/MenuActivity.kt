package com.itsrfid.cursokotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.itsrfid.cursokotlin.calculadoraimc.CalculadoraImcActivity
import com.itsrfid.cursokotlin.firstapp.FirstAppActivity
import com.itsrfid.cursokotlin.todoapp.TodoAppActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Elementos
        val btnSaludar : Button = findViewById(R.id.btn_saludapp)
        val btnImc : Button = findViewById(R.id.btnImc)
        val btnTodoApp : Button =  findViewById(R.id.btnTodo)

        //Eventos de click
        btnSaludar.setOnClickListener { navigateSaludapp() }
        btnImc.setOnClickListener { navigateImc() }
        btnTodoApp.setOnClickListener { navigateTodoApp() }
    }

    private fun navigateTodoApp(){
        val intent = Intent(this, TodoAppActivity::class.java)
        startActivity(intent)
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