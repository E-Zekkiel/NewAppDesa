package com.zekkiel.newappdesa.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.zekkiel.newappdesa.model.Pedido


@Dao
interface PedidoDao {

    @Query("SELECT * FROM pedido_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Pedido>> //getall

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pedido: Pedido)

    @Update
    suspend fun update(pedido: Pedido)

    @Delete
    suspend fun deletePedido(pedido: Pedido)

    @Query("DELETE FROM pedido_table")
    suspend fun deleteAll()
}
