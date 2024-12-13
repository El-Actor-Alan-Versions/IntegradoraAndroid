package mx.edu.utez.appbitacora.admin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import mx.edu.utez.appbitacora.R
import mx.edu.utez.appbitacora.databinding.ActivityEditarAlumnoBinding
import org.json.JSONObject

class EditarAlumno : AppCompatActivity() {
    private lateinit var binding : ActivityEditarAlumnoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarAlumnoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar3)
        val id = intent.getStringExtra("id")
        binding.edtName.setText(intent.getStringExtra("nombre"))
        binding.edtMatriculaU.setText(intent.getStringExtra("matricula"))
        binding.edtEmailU.setText(intent.getStringExtra("correo"))
        binding.edtCarreraU.setText(intent.getStringExtra("carrera"))
        binding.edtGradoGrupoU.setText(intent.getStringExtra("gradoG"))




        binding.btnUpdate.setOnClickListener {
            val nombre = binding.edtName.text.toString()
            val matricula = binding.edtMatriculaU.text.toString()
            val correo = binding.edtEmailU.text.toString()
            val gradoGrupo = binding.edtGradoGrupoU.text.toString()
            val carrera = binding.edtCarreraU.text.toString()

            val queue = Volley.newRequestQueue(this)
            val url = "http://192.168.1.68:8080/api/usuarios/"+id
            val metodo = Request.Method.PUT
            val body = JSONObject()
            body.put("matricula", matricula)
            body.put("correo", correo)
            body.put("nombre", nombre)
            body.put("carrera", carrera)
            body.put("grado_grupo", gradoGrupo)
            val listener = Response.Listener<JSONObject> {
                Toast.makeText(this,"Actualizacion Exitosa", Toast.LENGTH_LONG).show()
            }
            val errorListener = Response.ErrorListener {resul ->
                Toast.makeText(this,"Actualizacion Exitosa", Toast.LENGTH_LONG).show()
                Log.e("Error Actualizar", resul.toString())
            }
            val request = JsonObjectRequest(metodo, url, body, listener, errorListener)
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Deseas Actualizar este alumno?")
            builder.setMessage("Estas seguro que desas Actualizar este usuario")
            builder.setPositiveButton("No"){result,_ ->

            }
            builder.setNegativeButton("Si"){result,_ ->
                queue.add(request)
            }
            builder.show()
        }

        binding.btnDesactivar.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val url = "http://192.168.1.68:8080/api/usuarios/estatus/"+id
            val metodo = Request.Method.PUT
            val body = null
            val listener = Response.Listener<JSONObject> {
                Toast.makeText(this, "Eliminacion exitosa", Toast.LENGTH_LONG).show()
            }
            val errorListener = Response.ErrorListener { resut ->
                Log.e("ErrorDesactivar", resut.toString())
                Toast.makeText(this, "Ocurrio un error", Toast.LENGTH_LONG).show()
            }
            val request = JsonObjectRequest(metodo, url, body, listener, errorListener)
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Desactivar")
            builder.setMessage("Desaseas desactivar este usuario")
            builder.setNegativeButton("Si"){result,_ ->
                queue.add(request)
            }
            builder.setPositiveButton("Si"){result,_ ->

            }
            builder.show()
        }

        binding.btnRegresar.setOnClickListener {
            val intnet = Intent(this, MostrarUsuarios::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intnet)
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