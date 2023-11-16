package com.zekkiel.newappdesa.ui.add

import  android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zekkiel.newappdesa.model.Pedido
import com.zekkiel.newappdesa.R
import com.zekkiel.newappdesa.databinding.FragmentAgregarBinding
import com.zekkiel.newappdesa.viewModel.PedidoViewModel


class AgregarFragment : Fragment() {


    private lateinit var  binding: FragmentAgregarBinding
    private val pedidoViewModel by viewModels<PedidoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAgregarBinding.inflate(inflater, container, false)


        binding.formularioBotonAgregar.setOnClickListener {
            val nombre = binding.formularioNombre.text.toString()
            val direccion = binding.formularioDireccion.text.toString()
            val items = binding.formularioItems.text.toString()
            val comentario = binding.formularioComentario.text.toString()
            val total = binding.formularioTotal.text.toString()

            if (nombre.isNotEmpty() && direccion.isNotEmpty() && items.isNotEmpty() && total.isNotEmpty()) {
                val pedido = Pedido(id=0, nombre=nombre, direccion=direccion, items=items, comentario=comentario, total=total.toInt())

                pedidoViewModel.insertPedido(pedido = pedido)
                Log.d("AgregarFragment", "Pedido Creado! $pedido")

                findNavController().navigate(R.id.action_agregarFragment_to_listFragment)

            } else {
                Toast.makeText(requireContext(), "Complete los campos", Toast.LENGTH_SHORT).show()
            }


        }


        return binding.root
    }

}