package mx.edu.utez.appbitacora.registro

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import mx.edu.utez.appbitacora.adapter.RegistroAdapter
import mx.edu.utez.appbitacora.databinding.ActivityMostrarRegistrosBinding
import mx.edu.utez.appbitacora.model.Registro
import org.json.JSONObject

class MostrarRegistros : AppCompatActivity() {
    private lateinit var binding : ActivityMostrarRegistrosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarRegistrosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.bottomAppBar5)

        mostrarLabs()


    }

    fun mostrarLabs(){
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.68:8080/api/registros"
        val metodo = Request.Method.GET
        val listener = Response.Listener<JSONObject> { r ->
            val locate = r.getJSONObject("registroResponse").getJSONArray("registros")
            val list = mutableListOf<Registro>()
            for(i in 0 until locate.length()){
                list.add(
                    Registro(
                        locate.getJSONObject(i).getLong("id_registro"),
                        locate.getJSONObject(i).getString("fecha"),
                        locate.getJSONObject(i).getString("horaInicial"),
                        locate.getJSONObject(i).getString("horaFinal"),
                        locate.getJSONObject(i).getString("docente"),
                        locate.getJSONObject(i).getString("comentario"),
                        locate.getJSONObject(i).getBoolean("estatus"),
                        locate.getJSONObject(i).getJSONObject("usuario").getLong("id_usuario"),
                        locate.getJSONObject(i).getJSONObject("laboratorio").getLong("id_lab"),
                        locate.getJSONObject(i).getJSONObject("equipoComputo").getLong("id_equipo")
                    )
                )

            }
            val adater = RegistroAdapter(list)
            binding.rvRegistros.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.rvRegistros.adapter = adater
        }

        val errorListener = Response.ErrorListener { e ->
            Log.e("ErrorConsulta", e.message.toString())
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }

        val request = JsonObjectRequest(metodo, url, null, listener, errorListener)
        queue.add(request)
    }
}