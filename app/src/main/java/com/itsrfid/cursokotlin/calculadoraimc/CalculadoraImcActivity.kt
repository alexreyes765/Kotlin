package com.itsrfid.cursokotlin.calculadoraimc

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.itsrfid.cursokotlin.R

class CalculadoraImcActivity : AppCompatActivity() {

    private lateinit var male: CardView
    private lateinit var female: CardView
    private lateinit var txtheight: TextView
    private lateinit var rsRange: RangeSlider
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var btnSubsWeight: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var btnSubsAge: FloatingActionButton
    private lateinit var txtWeight: TextView
    private lateinit var txtAge: TextView
    private lateinit var btnCalculate : Button

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var weight = 50
    private var age = 0
    private var height = 120
    private val CHANNEL_ID = "notificacion_IMC"
    private val NOTIFICATION_ID = 1
    private var sms: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc_calculadora)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        crearCanalNotificaciones()
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        male = findViewById(R.id.male)
        female = findViewById(R.id.female)
        rsRange = findViewById(R.id.rsHeight)
        txtheight = findViewById(R.id.txtView)
        btnPlusAge = findViewById(R.id.btn_plusAge)
        btnSubsAge = findViewById(R.id.btn_subtractionAge)
        btnPlusWeight = findViewById(R.id.btn_plusWeight)
        btnSubsWeight = findViewById(R.id.btn_subtractionWeight)
        txtWeight = findViewById(R.id.txtWeight)
        txtAge = findViewById(R.id.txtAge)
        btnCalculate = findViewById(R.id.btn_calculate)
    }

    private fun initListeners() {
        male.setOnClickListener {
            chageGerder()
            setGenderColor()
        }
        female.setOnClickListener {
            chageGerder()
            setGenderColor()
        }

        rsRange.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            height = value.toInt()
            txtheight.text = "${df.format(value)} cm"
        }

        btnPlusWeight.setOnClickListener {
            weight += 1
            setWeight()
        }

        btnSubsWeight.setOnClickListener {
            weight -= 1
            setWeight()
        }

        btnPlusAge.setOnClickListener {
            age += 1
            setAge()
        }
        btnSubsAge.setOnClickListener {
            age -= 1
            setAge()
        }

        btnCalculate.setOnClickListener {
            calculaIMC()
        }
    }

    private fun calculaIMC() {
        val imc = weight / ((height / 100.0) * (height / 100.0))
        val df = DecimalFormat("#.##")
        val result = df.format(imc).replace(",", ".")
        val imcResult = result.toDouble()
        sms = when(imcResult){
            in 0.00..18.5->"Peso por debajo de lo normal"
            in 18.5..24.99->"Normal"
            in 25.00..29.9->"Peso superior al normal"
            else ->"obesidad"
        }
        mostrarNotificacion("Tu IMC esta lista!", "$imcResult $sms")
    }

    private fun setWeight() {
        if (weight < 0) {
            Toast.makeText(this, "El peso no debe ser menor a 0", Toast.LENGTH_LONG).show()
            weight = 0
        } else {
            txtWeight.text = "$weight kg"
        }
    }

    private fun setAge() {
        if (age < 0) {
            Toast.makeText(this, "La edad no debe ser menor a 0", Toast.LENGTH_LONG).show()
            age = 0
        } else {
            if (age == 1) {
                txtAge.text = "$age año"
            } else {
                txtAge.text = "$age años"
            }
        }
    }

    private fun chageGerder() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        male.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        female.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))

    }

    private fun getBackgroundColor(componentSelected: Boolean): Int {
        val colorReference = if (componentSelected) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)

    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }


    private fun crearCanalNotificaciones() {
        val name = "APP IMC"
        val descriptionText = "Notificaciones APPIMC"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }

        val notificationManager: NotificationManager =
            getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }

    private fun mostrarNotificacion(titulo: String, mensaje: String) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED){
                //si no tenemos permiso, pedirlo
                requestPermissions(arrayOf(android.Manifest.permission.POST_NOTIFICATIONS), 100)
                return
            }
        }
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(titulo)
            .setContentText(mensaje)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(NOTIFICATION_ID, builder.build())
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 100) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permiso concedido", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permiso denegado, no se pueden mostrar notificaciones", Toast.LENGTH_SHORT).show()
            }
        }
    }

}