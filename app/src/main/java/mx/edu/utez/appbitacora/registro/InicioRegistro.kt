package mx.edu.utez.appbitacora.registro

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import mx.edu.utez.appbitacora.admin.InsercionAlumnos
import mx.edu.utez.appbitacora.databinding.ActivityInicioRegistroBinding
import mx.edu.utez.appbitacora.model.Registro
import org.json.JSONObject

class InicioRegistro : AppCompatActivity() {
    private lateinit var binding: ActivityInicioRegistroBinding
    var idLab = ""
    var idAlumno = ""
    var idEquipo = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val codigo = intent.getStringExtra("qr")
        binding.txtAver.text = codigo.toString()

        binding.btnRegistroRegistro.setOnClickListener {
            val matricula = binding.edtMatricula.text.toString()
            val fecha = binding.edtFecha.text.toString()
            val horaIniciar = binding.edtHoraIni.text.toString()
            val horaFinal = binding.edtHoraFin.text.toString()
            val docente = binding.edtDocente.text.toString()
            val descri = binding.edtDescripReg.text.toString()

            if (fecha.isBlank() || horaFinal.isBlank() || horaIniciar.isBlank() ||
                docente.isBlank() || descri.isBlank() || matricula.isBlank()
            ) {
                Toast.makeText(this, "Por favor, llena todos los campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            obtenerAlumno(matricula) { alumnoObtenido ->
                if (alumnoObtenido) {
                    obtenerEquipo(codigo.toString()) { equipoObtenido ->
                        if (equipoObtenido && idAlumno.isNotEmpty() && idEquipo.isNotEmpty() && idLab.isNotEmpty()) {
                            try {
                                val registro = Registro(
                                    0,
                                    fecha,
                                    horaIniciar,
                                    horaFinal,
                                    docente,
                                    descri,
                                    true,
                                    idAlumno.toLong(),
                                    idEquipo.toLong(),
                                    idLab.toLong()
                                )
                                registrarRegistro(registro)
                            } catch (e: NumberFormatException) {
                                Toast.makeText(this, "Error al procesar los datos del registro.", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this, "Faltan datos para completar el registro.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Error al obtener datos del alumno.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnVolverRegistro.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    private fun registrarRegistro(registro: Registro) {
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.69:8080/api/registros"
        val metodo = Request.Method.POST

        val body = JSONObject().apply {
            put("fecha", registro.fecha)
            put("horaInicial", registro.horaIniciar)
            put("horaFinal", registro.horaFinal)
            put("docente", registro.docente)
            put("comentario", registro.comentario)
            put("estatus", registro.estado)
            put("usuario", JSONObject().apply {
                put("id_usuario", registro.idUsuario)
            })
            put("equipoComputo", JSONObject().apply {
                put("id_equipo", registro.idEquipo)
            })
            put("laboratorio", JSONObject().apply {
                put("id_lab", registro.idLab)
            })
        }

        val listener = Response.Listener<JSONObject> {
            Toast.makeText(this, "Registro completado exitosamente.", Toast.LENGTH_LONG).show()
        }

        val errorListener = Response.ErrorListener { e ->
            Log.e("ErrorInsert", e.message.toString())
            Log.d("RegistroBody", body.toString())
            Toast.makeText(this, "Error al registrar los datos.", Toast.LENGTH_LONG).show()
        }

        val request = JsonObjectRequest(metodo, url, body, listener, errorListener)
        queue.add(request)
    }


    private fun obtenerAlumno(matricula: String, onComplete: (Boolean) -> Unit) {
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.100.5:8080/api/usuarios/${matricula}@utez.edu.mx"
        val metodo = Request.Method.GET

        val listener = Response.Listener<JSONObject> { r ->
            try {
                idAlumno = r.getJSONObject("usuarioResponse")
                    .getJSONArray("usuario")
                    .getJSONObject(0)
                    .getInt("id_usuario")
                    .toString()
                onComplete(true)
            } catch (e: Exception) {
                Log.e("ErrorAlumno", e.message.toString())
                onComplete(false)
            }
        }

        val errorListener = Response.ErrorListener { e ->
            Log.e("ErrorAlumno", e.message.toString())
            Toast.makeText(this, "Error al obtener el alumno.", Toast.LENGTH_LONG).show()
            onComplete(false)
        }

        val request = JsonObjectRequest(metodo, url, null, listener, errorListener)
        queue.add(request)
    }

    private fun obtenerEquipo(codigo: String, onComplete: (Boolean) -> Unit) {
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.69:8080/api/equipos/${codigo}"
//        val url = "http://192.168.100.5:8080/api/equipos/51501CE-19-039"
        val metodo = Request.Method.GET

        val listener = Response.Listener<JSONObject> { r ->
            try {
                val locate = r.getJSONObject("equipoComputoResponse")
                    .getJSONArray("equipoComputos")
                    .getJSONObject(0)
                idLab = locate.getJSONObject("laboratorio").getLong("id_lab").toString()
                idEquipo = locate.getLong("id_equipo").toString()
                Toast.makeText(this, "Computadora Encontrada.", Toast.LENGTH_SHORT).show()
                onComplete(true)
            } catch (e: Exception) {
                Log.e("ErrorEquipo", e.message.toString())
                onComplete(false)
            }
        }

        val errorListener = Response.ErrorListener { e ->
            Log.e("ErrorEquipo", e.message.toString())
            Toast.makeText(this, "Error al obtener el equipo.", Toast.LENGTH_LONG).show()
            onComplete(false)
        }

        val request = JsonObjectRequest(metodo, url, null, listener, errorListener)
        queue.add(request)
    }
}
