package mx.edu.utez.appbitacora.model

data class Registro (
    val fecha : String,
    val horaIniciar : String,
    val horaFinal : String,
    val docente : String,
    val comentario : String,
    val estado : Boolean,
    val idUsuario : Int,
    val idEquipo : Int,
    val idLab : Int
)