package mx.edu.utez.appbitacora.equipos

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import mx.edu.utez.appbitacora.databinding.ActivityRegistroEquipoBinding
import org.json.JSONObject

class RegistroEquipo : AppCompatActivity() {
    private lateinit var binding: ActivityRegistroEquipoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroEquipoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegistroEqipo.setOnClickListener {
            val num = binding.edtNumeroEquipo.text.toString().trim()
            val codigo = binding.edtCodigo.text.toString().trim()

            if (num.isNotEmpty() && codigo.isNotEmpty()) {
                try {
                    val numeroEnLab = num.toInt() // Validar que sea un número
                    enviarDatos(numeroEnLab, codigo)
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "El número ingresado no es válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun enviarDatos(numeroEnLab: Int, codigo: String) {
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.100.5:8080/api/equipos"
        val metodo = Request.Method.POST

        // Crear el cuerpo JSON con el formato esperado
        val body = JSONObject().apply {
            put("numeroEnLab", numeroEnLab)
            put("codigo", codigo)
            put("estatus", true)
            put("laboratorio", JSONObject().apply {
                put("id_lab", 1)
            })
        }

        val listener = Response.Listener<JSONObject> { response ->
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            Log.d("Response", response.toString())
        }

        val errorListener = Response.ErrorListener { error ->
            Log.e("Error", error.message ?: "Error desconocido")
            Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
        }

        val request = JsonObjectRequest(metodo, url, body, listener, errorListener)
        queue.add(request)
    }
}
