package mx.edu.utez.appbitacora.registro

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import mx.edu.utez.appbitacora.R
import mx.edu.utez.appbitacora.databinding.ActivityInicioRegistroBinding
import org.json.JSONObject

class InicioRegistro : AppCompatActivity() {
    private lateinit var binding: ActivityInicioRegistroBinding
    var idLab = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioRegistroBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_inicio_registro)

        var codigo = intent.getStringExtra("qr_result").toString()
        obtenerEquipo(codigo)
        if (idLab.isNullOrEmpty()){
            
        }

    }

    fun obtenerEquipo(codigo: String) {
        val queue = Volley.newRequestQueue(this)
        val metodo = Request.Method.GET
        val url = "http://192.168.100.5:8080api/equipos/${codigo}"

        val listener = Response.Listener<JSONObject> { r ->
            val locate = r.getJSONObject("equipoComputoResponse").getJSONArray("equipoComputos")
                .getJSONObject(0)
            idLab = locate.getJSONObject("laboratorio").getLong("id_lab").toString()
            Toast.makeText(this, "Computadora Encontrada", Toast.LENGTH_SHORT).show()
        }

        val errorListener = Response.ErrorListener { e ->
            Log.e("ErrrorListener", e.message.toString())
            Toast.makeText(this,"Compuradora No encontrada", Toast.LENGTH_SHORT).show()
        }

        val request = JsonObjectRequest(metodo, url, null, listener, errorListener)
        queue.add(request)

    }
}
