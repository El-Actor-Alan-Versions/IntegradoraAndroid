package mx.edu.utez.appbitacora.model

data class Equipo (
    val idEquipo : Long,
    val numeroEnLab : Int,
    val codigo : String,
    val estatus : Boolean,
    val idLab : Long
)