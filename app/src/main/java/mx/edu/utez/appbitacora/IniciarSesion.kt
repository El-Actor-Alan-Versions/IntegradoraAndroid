package mx.edu.utez.appbitacora

import android.app.DownloadManager.Request
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import mx.edu.utez.appbitacora.admin.MenuAdmin
import mx.edu.utez.appbitacora.databinding.ActivityIniciarSesionBinding
import mx.edu.utez.appbitacora.registro.InicioRegistro
import mx.edu.utez.appbitacora.registro.MainActivity
import org.json.JSONObject

class IniciarSesion : AppCompatActivity() {
    private lateinit var binding : ActivityIniciarSesionBinding
    private var user = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIniciar.setOnClickListener{
            val email = binding.edtEmailInc.text
            val pass = binding.editTextTextPassword.text
            if(!email.isNullOrEmpty() && !pass.isNullOrEmpty()) {
                login(email.toString(), pass.toString())
            }else{
                Toast.makeText(this,"Campo/s vacios", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun login(email : String, password : String){
        val queue = Volley.newRequestQueue(this)
        val metodo = com.android.volley.Request.Method.POST
        val url = "http://192.168.100.5:8080/api/login?correo=${email}@utez.edu.mx&contrasenia=${password}"
        val listener = Response.Listener<JSONObject> { r ->
            user = r.getJSONObject("usuarioResponse").getJSONArray("usuario").getJSONObject(0).getString("rol")

            if(!user.isNullOrEmpty()){
                Toast.makeText(this,"Bienvenido "+"${r.getJSONObject("usuarioResponse").getJSONArray("usuario").getJSONObject(0).getString("nombre")}",
                    Toast.LENGTH_LONG).show()
            }

            if(user.equals("Alumno")){
                val intnet = Intent(this, MainActivity::class.java)
                intnet.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intnet)
            }else if (user.equals("Admin")){
                val intnet = Intent(this, MenuAdmin::class.java)
                intnet.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intnet)
            }
        }

        val errorListener = Response.ErrorListener { e ->
            Toast.makeText(this,"Error en usuario o contraseña ", Toast.LENGTH_LONG).show()
            Log.e("ErrorLogin", e.message.toString())
        }

        val request = JsonObjectRequest(metodo, url, null, listener, errorListener)
        queue.add(request)

    }
}