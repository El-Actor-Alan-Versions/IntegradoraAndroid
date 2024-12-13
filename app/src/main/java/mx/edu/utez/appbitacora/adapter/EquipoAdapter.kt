package mx.edu.utez.appbitacora.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.edu.utez.appbitacora.adapter.LabsAdapter.ViewHolder
import mx.edu.utez.appbitacora.databinding.LayoutAdapterEquipoBinding
import mx.edu.utez.appbitacora.databinding.LayoutAdapterLabsBinding
import mx.edu.utez.appbitacora.model.Equipo

class EquipoAdapter (var list : MutableList<Equipo>) :
RecyclerView.Adapter<EquipoAdapter.ViewHolder>(){

    class ViewHolder (var binding : LayoutAdapterEquipoBinding) :
    RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutAdapterEquipoBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var equipo = list[position]
        with(holder.binding){
            txtCidigo.text = equipo.codigo
            txtNum.text = equipo.numeroEnLab.toString()
        }
    }
}