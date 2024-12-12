package mx.edu.utez.appbitacora.admin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
        setSupportActionBar(binding.toolbar2)

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
                val url = "http://192.168.106.3:8080/api/usuarios"
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
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_admin, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itmMostrar ->{
                val intent = Intent(this, MostrarUsuarios::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            R.id.itmRegistro ->{
                val intent = Intent(this, InsercionAlumnos::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            R.id.itmInicio ->{
                val intent = Intent(this, MenuAdmin::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}