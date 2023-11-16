package com.zekkiel.newappdesa.model


import java.io.Serializable



data class PedidoExample(
    val id:Int,
    val nombre:String,
    val direccion:String,
    val items:String,
    val comentario:String,
    val total:Int
) : Serializable
