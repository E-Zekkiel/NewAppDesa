package com.zekkiel.newappdesa.ui

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zekkiel.newappdesa.model.Pedido
import com.zekkiel.newappdesa.R
import com.zekkiel.newappdesa.databinding.FragmentListBinding
import com.zekkiel.newappdesa.viewModel.PedidoViewModel


class ListFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentListBinding

    private val pedidoViewModel by viewModels<PedidoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)


        val adapter = PedidosAdapter()
        binding.recycleViewPedidos.layoutManager = LinearLayoutManager(requireContext())
        binding.recycleViewPedidos.adapter = adapter

        val divider = DividerItemDecoration(requireContext(), LinearLayoutManager(requireContext()).orientation)
        binding.recycleViewPedidos.addItemDecoration(divider)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        binding.pedidoAgregar.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_agregarFragment)
        }

        pedidoViewModel.readAllData.observe(viewLifecycleOwner) {pedidoList ->
            adapter.setList(pedidos = pedidoList)
        }

        return binding.root
    }

    private fun deleteAll() {
        val dialog = AlertDialog.Builder(requireContext())

        dialog.setTitle("¿Eliminar Todos?")
        dialog.setMessage("¿Esta seguro que desea eliminar a todos los usuarios?")

        dialog.setNegativeButton("No") { _,_ ->
            return@setNegativeButton
        }

        dialog.setPositiveButton("Si") { _,_ ->
            pedidoViewModel.deleteAllPedidos()
        }

        dialog.create().show()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.menu_delete -> {
                deleteAll()
                true
            }

            else -> {
                false
            }
        }
    }
}