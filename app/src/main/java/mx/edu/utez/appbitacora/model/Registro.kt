package mx.edu.utez.appbitacora.model

data class Registro(
    val id : Long,
    val fecha: String,
    val horaIniciar: String,
    val horaFinal: String,
    val docente: String,
    val comentario: String,
    val estado: Boolean,
    val idUsuario: Long,
    val idEquipo: Long,
    val idLab: Long
)