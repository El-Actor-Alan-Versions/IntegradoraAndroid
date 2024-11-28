package mx.edu.utez.appbitacora.admin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import mx.edu.utez.appbitacora.adapter.UsuarioAdapter
import mx.edu.utez.appbitacora.databinding.ActivityMostrarUsuariosBinding
import mx.edu.utez.appbitacora.model.Usuario
import org.json.JSONObject

class MostrarUsuarios : AppCompatActivity() {
    private lateinit var binding: ActivityMostrarUsuariosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.107.122:8080/api/Allusuarios"
        val body = null
        val metodo = Request.Method.GET

        val listener = Response.Listener<JSONObject> { result ->
            val list = mutableListOf<Usuario>()
            val locate = result.getJSONObject("usuarioResponse").getJSONArray("usuario")
            for (i in 0 until locate.length()){
                list.add(
                    Usuario(
                        locate.getJSONObject(i).getLong("id_usuario"),
                        locate.getJSONObject(i).getString("matricula"),
                        locate.getJSONObject(i).getString("correo"),
                        locate.getJSONObject(i).getString("nombre"),
                        locate.getJSONObject(i).getString("carrera") ,
                        locate.getJSONObject(i).getString("grado_grupo"),
                        locate.getJSONObject(i).getString("contrasenia"),
                        locate.getJSONObject(i).getString("rol"),
                        locate.getJSONObject(i).getBoolean("estatus").toString())
                )
            }
            val adapter = UsuarioAdapter(list)
            binding.rvUsuarios.adapter = adapter
            binding.rvUsuarios.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            Toast.makeText(this, "Bien",Toast.LENGTH_SHORT).show()
        }
        val errorListener = Response.ErrorListener { result ->
            Toast.makeText(this, "No", Toast.LENGTH_LONG).show()
        }

        val request =JsonObjectRequest(metodo, url, body, listener, errorListener)
        queue.add(request)



    }
}