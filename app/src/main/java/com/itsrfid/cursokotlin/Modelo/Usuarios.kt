package com.itsrfid.cursokotlin.Modelo

import androidx.core.app.NotificationCompat.MessagingStyle.Message

class Usuarios {
    var nombre: String
    var apellido: String
    var correo: String
    var password: String

    constructor(
        nombre: String = "Defaul",
        apellido: String = "Default",
        correo: String = "Defaul.com",
        pass: String = "password"
    ) {
        this.nombre = nombre
        this.apellido = apellido
        this.correo = correo
        password = pass
    }

    fun compararDatos() {
        if (correo == "a.reyes@itsrfid.com" && password == "ctmp") {
            println("Bienvenido $nombre $apellido")
        } else {
            println("Buen intento $correo pero no estas registrado")
            println("¿Deseas registrarte?")
            val respuesta = readln()
            if (respuesta == "si" || respuesta == "Si") {
                println("Bienvenido $nombre, se han registrado tus datos exitosamente")
                println(
                    "Nombre: $nombre \n" +
                            "Apellido: $apellido \n" +
                            "Correo: $correo \n" +
                            "Contraseña: $password"
                )
            }
        }
    }

    fun ejercicio1(numberOfMessage: Int) {
        when (numberOfMessage) {
            in 1..99 -> println("Hola $nombre tienes $numberOfMessage notificaciones")
            else -> println("Hola $nombre tienes 99+ notificaciones... andale revisalos")
        }
    }

    fun ejecicio2(age: Int, isMonday: Boolean): Int {
        return when (age) {
            in 0..12 -> 15
            in 13..60 -> if (isMonday) 25 else 30
            in 61..100 -> 20
            else -> -1
        }
    }
}