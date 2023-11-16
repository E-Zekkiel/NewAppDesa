package com.zekkiel.newappdesa.ui.uptade

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.zekkiel.newappdesa.model.Pedido
import com.zekkiel.newappdesa.R
import com.zekkiel.newappdesa.databinding.FragmentActualizarBinding
import com.zekkiel.newappdesa.viewModel.PedidoViewModel


class ActualizarFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentActualizarBinding
    private val pedidoViewModel by viewModels<PedidoViewModel>()

    private var pedido: Pedido? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActualizarBinding.inflate(inflater, container, false)

        pedido = arguments?.getSerializable("pedido") as Pedido

        binding.formularioNombre.setText(pedido?.let { it.nombre })
        binding.formularioDireccion.setText(pedido!!.direccion)
        binding.formularioItems.setText(pedido!!.items)
        binding.formularioComentario.setText(pedido!!.comentario)
        binding.formularioTotal.setText(pedido!!.total)


        binding.formularioBotonActualizar.setOnClickListener {validateFields(pedido!!)}

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)


        return binding.root
    }

    private fun validateFields(pedido: Pedido){
        val nombre = binding.formularioNombre.text.toString()
        val direccion = binding.formularioDireccion.text.toString()
        val items = binding.formularioItems.text.toString()
        val comentario = binding.formularioComentario.text.toString()
        val total = binding.formularioTotal.text.toString()

        if (nombre.isNotEmpty() && direccion.isNotEmpty() && items.isNotEmpty() && total.isNotEmpty()) {

            val pedido = pedido.copy(nombre = nombre, direccion = direccion, items = items, comentario = comentario, total = total.toInt())
            pedidoViewModel.updatePedido(pedido = pedido)

            findNavController().navigate(R.id.action_actualizarFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Complete los campos", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.menu_delete -> {
                deletePedido()
                true
            }

            else -> {
                false
            }
        }
    }

    private fun deletePedido(){
        val dialog = AlertDialog.Builder(requireContext())

        dialog.setTitle("¿Desea Eliminar?")
        dialog.setMessage("Esta seguro que desea eliminar el pedido de: ${pedido!!.nombre}")

        dialog.setNegativeButton(getString(R.string.no_option)) { _,_ ->
            return@setNegativeButton
        }

        dialog.setPositiveButton("Si") { _,_ ->
            pedidoViewModel.deletePedido(pedido = pedido!!)
            Toast.makeText(requireContext(), "pedido eliminado!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_actualizarFragment_to_listFragment)
        }

        dialog.create().show()
    }

}