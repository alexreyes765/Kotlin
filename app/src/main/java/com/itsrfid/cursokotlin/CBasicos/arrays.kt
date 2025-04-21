package com.itsrfid.cursokotlin.CBasicos

/*
* Maneras de recorrer un array usando el bucle for
*
* for(position in nombreArray.indeces){
*   aqui va codigo
* }
* for((posicion, valor) in nombreArray.withIndex()){
*   aqui va codigo
* }
* for(nombreQueLeQuieresAsignarALaVariable in nombreArray){
*   codigo aqui
* }*/

fun main(){
    val weekDays = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    for (i in weekDays.indices){
        println("${i+1} = ${weekDays[i]}")

    }

}