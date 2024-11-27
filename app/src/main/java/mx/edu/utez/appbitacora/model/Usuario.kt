package mx.edu.utez.appbitacora.model

data class Usuario(
        val id : Long,
        val matricula : String,
        val correo : String,
        val nombre : String,
        val carrera : String,
        val gradoGrupo : String,
        val contrasenia : String,
        val rol : String,
        val status : String
        )