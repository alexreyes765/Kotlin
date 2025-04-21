package com.itsrfid.cursokotlin.CBasicos

fun main() {
    //inmutablesList()
    //mutableList()

    /*val lista = mutableListOf(1,2,3,4,5)
    lista.remove(1)
    println(lista)*/
}

fun mutableList() {
    val weekDays: MutableList<String> = mutableListOf("lunes", "martes", "miercoles", "jueves", "viernes")

    //se usa para iterar en la lista, pero en lugar de usar it se poner el nombre de una variable
    //weekDays.forEach { days -> print(days) }

    //con .add se agregan elementos a la lista, si se le agrega un numero indica el indice donde se va a agragar
    //weekDays.add("sabado")

    //iterar en la lista pero usa el it
    //weekDays.forEach { print(it) }
}

fun inmutablesList() {
    val readOnly: List<String> = listOf("lunes", "martes", "miercoles", "jueves", "viernes")

    //muestra el ultimo elemento de la lista
    //println(readOnly.last())

    //muestra el primer elemento de la lista
    //println(readOnly.first())

    //filtra lo que se pone en el contain
    //println(readOnly.filter { it.contains("n") })


}