package com.zekkiel.newappdesa.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zekkiel.newappdesa.model.Pedido
import com.zekkiel.newappdesa.repository.PedidoRepository
import kotlinx.coroutines.launch

class PedidoViewModel: ViewModel() {

    private val repository = PedidoRepository()

    val readAllData: LiveData<List<Pedido>> = repository.readAllData

    fun insertPedido(pedido: Pedido) {
        viewModelScope.launch {
            repository.insertPedido(pedido = pedido)
        }
    }


    fun updatePedido(pedido: Pedido) {
        viewModelScope.launch {
            repository.updatePedido(pedido = pedido)
        }
    }


    fun deletePedido(pedido: Pedido) {
        viewModelScope.launch {
            repository.deletePedido(pedido = pedido)
        }
    }


    fun deleteAllPedidos() {
        viewModelScope.launch {
            repository.deleteAllPedidos()
        }
    }

}