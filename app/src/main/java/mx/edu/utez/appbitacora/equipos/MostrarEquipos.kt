package mx.edu.utez.appbitacora.equipos

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import mx.edu.utez.appbitacora.R
import mx.edu.utez.appbitacora.adapter.EquipoAdapter
import mx.edu.utez.appbitacora.databinding.ActivityMostrarEquiposBinding
import mx.edu.utez.appbitacora.model.Equipo
import org.json.JSONObject

class MostrarEquipos : AppCompatActivity() {
    private  lateinit var binding : ActivityMostrarEquiposBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarEquiposBinding.inflate(layoutInflater)
        setContentView(binding.root)
        verEquipos()

    }

    fun verEquipos (){
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.68:8080/api/equipos"
        val body = null
        val metodo = Request.Method.GET
        val listener = Response.Listener<JSONObject> { r ->
            val locate = r.getJSONObject("equipoComputoResponse").getJSONArray("equipoComputos")
            val list = mutableListOf<Equipo>()
            for(i in 0 until locate.length()){
                list.add(
                    Equipo(
                        locate.getJSONObject(i).getLong("id_equipo"),
                        locate.getJSONObject(i).getInt("numeroEnLab"),
                        locate.getJSONObject(i).getString("codigo"),
                        locate.getJSONObject(i).getBoolean("estatus"),
                        locate.getJSONObject(i).getJSONObject("laboratorio").getLong("id_lab")
                    )
                )
                val adapater = EquipoAdapter(list)
                binding.rvEquipos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                binding.rvEquipos.adapter = adapater
                Toast.makeText(this, "Si", Toast.LENGTH_SHORT).show()

            }
        }
        val errorListener = Response.ErrorListener { e ->
            Log.e("Error", e.message.toString())
            Toast.makeText(this, "No", Toast.LENGTH_SHORT).show()
        }

        val request = JsonObjectRequest(metodo, url, body, listener, errorListener)
        queue.add(request)
    }
}