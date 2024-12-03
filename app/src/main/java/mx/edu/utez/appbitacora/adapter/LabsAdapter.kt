package mx.edu.utez.appbitacora.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.edu.utez.appbitacora.databinding.LayoutAdapterLabsBinding
import mx.edu.utez.appbitacora.model.Labs

class LabsAdapter (val list : MutableList<Labs>) : RecyclerView.Adapter<LabsAdapter.ViewHolder>() {
    var onClick : ((Labs) -> Unit)? = null

    class ViewHolder (val binding : LayoutAdapterLabsBinding) :
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabsAdapter.ViewHolder {
        return ViewHolder(LayoutAdapterLabsBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: LabsAdapter.ViewHolder, position: Int) {
        val lab = list[position]
        var estatus = "Inactivo"
        if(lab.estatus){
            estatus = "Activo"
        }
        with(holder.binding){
            txtId.text = lab.id.toString()
            txtNomL.text = lab.nomLab
            txtDocL.text = lab.docencia.toString()
            txtEstaL.text = estatus
        }

        holder.itemView.setOnClickListener{
            onClick?.invoke(lab)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}