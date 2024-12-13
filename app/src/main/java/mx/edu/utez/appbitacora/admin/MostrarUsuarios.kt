package mx.edu.utez.appbitacora.admin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import mx.edu.utez.appbitacora.R
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
        setSupportActionBar(binding.toolbar4)


        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.68:8080/api/usuarios"
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
            adapter.onItemClick = {usuario ->
                val intent = Intent( this, EditarAlumno::class.java)
                intent.putExtra("id",usuario.id.toString())
                intent.putExtra("nombre",usuario.nombre)
                intent.putExtra("matricula",usuario.matricula)
                intent.putExtra("correo",usuario.correo)
                intent.putExtra("carrera",usuario.carrera)
                intent.putExtra("gradoG",usuario.gradoGrupo)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

            }
        }

        val errorListener = Response.ErrorListener { result ->
            Toast.makeText(this, "No", Toast.LENGTH_LONG).show()
        }

        val request =JsonObjectRequest(metodo, url, body, listener, errorListener)
        queue.add(request)



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