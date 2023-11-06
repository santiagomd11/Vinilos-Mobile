package com.example.vinilos.models

data class Musician(
    val id:Int,
    val name:String,
    val image:String,
    val description:String,
    val birthDate:String,
    //val albums:Array<String>, TODO: parse values
    //val performerPrizes:Array<String>
)