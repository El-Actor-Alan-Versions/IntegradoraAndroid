package mx.edu.utez.appbitacora.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.edu.utez.appbitacora.adapter.UsuarioAdapter.ViewHolder
import mx.edu.utez.appbitacora.databinding.LayoutRegistroBinding
import mx.edu.utez.appbitacora.databinding.LayoutUsuarioBinding
import mx.edu.utez.appbitacora.model.Labs
import mx.edu.utez.appbitacora.model.Registro
import mx.edu.utez.appbitacora.model.Usuario

class RegistroAdapter (val list : MutableList<Registro>) : RecyclerView.Adapter<RegistroAdapter.ViewHolder>() {
    var onItemClick : ((Registro) -> Unit)? = null

    class ViewHolder (val binding: LayoutRegistroBinding) :
    RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):RegistroAdapter.ViewHolder {
        //crea la vista, infla el xml del layout_contacto
        return ViewHolder(LayoutRegistroBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }

    override fun getItemCount(): Int {
        //Devuelve la cantidad de elementos
        return list.size
    }


    override fun onBindViewHolder(holder: mx.edu.utez.appbitacora.adapter.RegistroAdapter.ViewHolder, position: Int) {
        //Llena la lista con los datos de la lista
        val registro = list[position]
        with(holder.binding){
            txtFechaRegistro.text = registro.fecha
            txtDocenteRegistro.text = registro.docente
            txtHoraFinal.text = registro.horaFinal
            txtHoraInicio.text = registro.horaIniciar
            txtDescripcion.text = registro.comentario

        }

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(registro)
        }
    }


}