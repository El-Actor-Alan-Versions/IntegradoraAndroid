package mx.edu.utez.appbitacora.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import mx.edu.utez.appbitacora.databinding.LayoutUsuarioBinding
import mx.edu.utez.appbitacora.model.Usuario

class UsuarioAdapter(var list: MutableList<Usuario>): RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {
    var onItemClick : ((Usuario) -> Unit)? = null
    class ViewHolder(val binding: LayoutUsuarioBinding):
        RecyclerView.ViewHolder(binding.root){
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //crea la vista, infla el xml del layout_contacto
        return ViewHolder(LayoutUsuarioBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    }



    override fun getItemCount(): Int {
        //Devuelve la cantidad de elementos
        return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Llena la lista con los datos de la lista
        val usuario = list[position]
        with(holder.binding){
            txtMatricula.text= usuario.matricula
            txtNombre.text = usuario.nombre
            txtCorreo.text = usuario.correo
            txtCarrera.text = usuario.correo
            txtRol.text = usuario.rol
            txtGraadoG.text = usuario.gradoGrupo
        }
        holder.itemView.setOnClickListener{
            onItemClick?.invoke(usuario)
        }
    }


}