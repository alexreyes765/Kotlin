package com.itsrfid.cursokotlin.firstapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.itsrfid.cursokotlin.R

class FirstAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)

        val btnStart = findViewById<Button>(R.id.btn_click)
        val etName = findViewById<EditText>(R.id.et_text)
        btnStart.setOnClickListener {
            val name = etName.text.toString()
            navigateToResult(name)

        }

    }

    override fun onResume() {
        super.onResume()

        val etName: EditText = findViewById(R.id.et_text)
        etName.requestFocus()
        etName.text.clear()
    }

    private fun navigateToResult(nombre: String){
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("Nombre_Extra", nombre)
        startActivity(intent)
    }

}