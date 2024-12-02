package mx.edu.utez.appbitacora.admin

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
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
import mx.edu.utez.appbitacora.databinding.ActivityInsercionAlumnosBinding
import org.json.JSONObject

class InsercionAlumnos : AppCompatActivity() {
    private lateinit var binding : ActivityInsercionAlumnosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsercionAlumnosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf("Alumno","Admin","Soporte")
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, list)

        binding.SpinerRoles.adapter = adapter

        binding.btnRegistrarUsuario.setOnClickListener {
            val nombre = binding.edtNombre.text.toString()
            val matricula = binding.edtMatricula.text.toString()
            val correo = binding.edtCorreo.text.toString()
            val gradoGrupo = binding.edtGradoGrupo.text.toString()
            val carrera = binding.edtCarrera.text.toString()
            val contra = binding.edtPassword.text.toString()
            val rol = binding.SpinerRoles.selectedItem.toString()

            if(!nombre.isNullOrEmpty()&& !matricula.isNullOrEmpty() && !contra.isNullOrEmpty() &&
                !correo.isNullOrEmpty() && !gradoGrupo.isNullOrEmpty() && !carrera.isNullOrEmpty() && !rol.isNullOrEmpty()){

                val queue = Volley.newRequestQueue(this)
                val url = "http://192.168.105.43:8080/api/usuarios"
                val metodo = Request.Method.POST
                val body = JSONObject()
                body.put("matricula", matricula)
                body.put("correo", correo)
                body.put("nombre", nombre)
                body.put("carrera", carrera)
                body.put("grado_grupo", gradoGrupo)
                body.put("contrasenia", contra)
                body.put("rol",rol)
                body.put("estatus", true)

                val listener = Response.Listener<JSONObject> { result ->
                    Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_LONG).show()
                    Log.d("Registro", result.toString())
                }
                val errorListener = Response.ErrorListener { result ->
                    Toast.makeText(this, "Ocurrio un error", Toast.LENGTH_LONG).show()
                    Log.d("ErrorInsert", result.toString())
                }

                val request = JsonObjectRequest(metodo, url, body, listener, errorListener)
                queue.add(request)

            }

        }

    }
}