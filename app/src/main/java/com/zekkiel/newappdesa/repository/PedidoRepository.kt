package com.zekkiel.newappdesa.repository

import androidx.lifecycle.LiveData
import com.zekkiel.newappdesa.model.Pedido
import com.zekkiel.newappdesa.data.PedidoDB

class PedidoRepository {


    private val pedidoDao = PedidoDB.getDataBase().pedidoDao()

    val readAllData: LiveData<List<Pedido>> = pedidoDao.readAllData()


    suspend fun insertPedido(pedido: Pedido) {
        pedidoDao.insert(pedido = pedido)
    }


    suspend fun updatePedido(pedido: Pedido) {
        pedidoDao.update(pedido = pedido)
    }


    suspend fun deletePedido(pedido: Pedido) {
        pedidoDao.deletePedido(pedido = pedido)
    }

    suspend fun deleteAllPedidos() {
        pedidoDao.deleteAll()
    }
}