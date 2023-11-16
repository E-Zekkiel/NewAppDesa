package com.zekkiel.newappdesa.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.zekkiel.newappdesa.R
import com.zekkiel.newappdesa.databinding.ItemPedidoBinding
import com.zekkiel.newappdesa.model.Pedido

class PedidosAdapter(): RecyclerView.Adapter<PedidosAdapter.PedidosViewHolder>() {

    private var pedidoList = emptyList<Pedido>()

    inner class PedidosViewHolder(private val binding: ItemPedidoBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(pedido: Pedido){
            with(binding){
                pedidoId.text = pedido.id.toString()
                pedidoName.text = pedido.nombre
                pedidoDireccion.text = pedido.direccion
                pedidoTotal.text = pedido.total.toString()

                root.setOnClickListener {

                    val bundle = Bundle()
                    bundle.putSerializable("pedido", pedido)
                    itemView.findNavController().navigate(R.id.action_listFragment_to_actualizarFragment, bundle)
                }
            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PedidosViewHolder {
        val binding = ItemPedidoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PedidosViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: PedidosViewHolder, position: Int) {
        val pedido = pedidoList.get(position)
        holder.bind(pedido = pedido)
    }

    override fun getItemCount(): Int {
        return pedidoList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(pedidos: List<Pedido>) {
        pedidoList = pedidos
        notifyDataSetChanged()
    }
}