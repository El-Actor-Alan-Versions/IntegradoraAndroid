package mx.edu.utez.appbitacora.laboratorio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
import mx.edu.utez.appbitacora.adapter.LabsAdapter
import mx.edu.utez.appbitacora.databinding.ActivityMostrarLabsBinding
import mx.edu.utez.appbitacora.model.Labs
import mx.edu.utez.appbitacora.admin.MenuAdmin
import mx.edu.utez.appbitacora.laboratorio.RegistrarLabs
import org.json.JSONObject

class MostrarLabs : AppCompatActivity() {
    private lateinit var binding : ActivityMostrarLabsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarLabsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.bottomAppBar9)

        val queue = Volley.newRequestQueue(this)
        val metodo = Request.Method.GET
        val url = "http://192.168.1.68:8080/api/labsAll"
        val listener = Response.Listener<JSONObject> { result ->
            val list = mutableListOf<Labs>()
            val locate = result.getJSONObject("laboratorioResponse").getJSONArray("laboratorios")
            for(i in 0 until locate.length()){
                list.add(
                    Labs(
                        locate.getJSONObject(i).getLong("id_lab"),
                        locate.getJSONObject(i).getString("nombre_lab"),
                        locate.getJSONObject(i).getInt("docencia"),
                        locate.getJSONObject(i).getBoolean("estatus")
                    )
                )
            }
            val adapter = LabsAdapter(list)
            binding.rvLabs.adapter = adapter
            binding.rvLabs.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false)
            adapter.onClick = {labs ->
                val intent = Intent(this, EditarLabs::class.java)
                intent.putExtra("id", labs.id.toString())
                Toast.makeText(this, "Exito", Toast.LENGTH_LONG).show()
                startActivity(intent)
            }


        }
        val errorListener = Response.ErrorListener { result ->
            Toast.makeText(this, "ocurrio un error", Toast.LENGTH_LONG).show()
            Log.e("ErrorMostrar", result.toString())
        }

        val request = JsonObjectRequest(metodo, url ,null, listener,errorListener)
        queue.add(request)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_labs, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itmMostrarLab->{
                val intent = Intent(this, MostrarLabs::class.java)
                startActivity(intent)
            }
            R.id.itmRegistraLab->{
                val intent = Intent(this, RegistrarLabs::class.java)
                startActivity(intent)
            }
            R.id.itmSalirLab->{
                val intent = Intent(this, MenuAdmin::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}