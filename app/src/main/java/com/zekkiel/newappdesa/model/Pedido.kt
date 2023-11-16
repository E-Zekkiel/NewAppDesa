package com.zekkiel.newappdesa.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "pedido_table")
data class Pedido(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name = "nombre")
    val nombre:String,
    @ColumnInfo(name = "direccion")
    val direccion:String,
    @ColumnInfo(name = "compras")
    val items:String,
    @ColumnInfo(name = "comentario")
    val comentario:String,
    @ColumnInfo(name = "total")
    val total:Int
) : Serializable
