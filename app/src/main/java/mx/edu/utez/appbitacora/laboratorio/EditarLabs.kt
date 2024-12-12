package mx.edu.utez.appbitacora.laboratorio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
import mx.edu.utez.appbitacora.databinding.ActivityEditarLabsBinding
import mx.edu.utez.appbitacora.model.Labs
import org.json.JSONObject

class EditarLabs : AppCompatActivity() {
    private lateinit var binding : ActivityEditarLabsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarLabsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.bottomAppBar2)
        val id = intent.getStringExtra("id").toString().toLong()

        binding.btnActualizaLabs.setOnClickListener {
            val lab =Labs(
                id,
                binding.edtNombreLapU.text.toString(),
                binding.edtDocenciaU.text.toString().toInt(),
                true
            )
            editarLabs(id, lab)
        }

    }

    fun editarLabs(id : Long, lab : Labs){
        val queue = Volley.newRequestQueue(this)
        Log.i("Ayuda", id.toString())
        val metodo = Request.Method.PUT
        val url = "http://192.168.111.30:8080/api/labs/"+id
        val body = JSONObject()
        body.put("id_lab", lab.id)
        body.put("nombre_lab",lab.nomLab)
        body.put("docencia", lab.docencia)
        body.put("estatus", lab.estatus)

        val listener = Response.Listener<JSONObject> { result ->
            if(result.getJSONArray("metadata").getJSONObject(0).getString("codigo").equals("00")){
                Toast.makeText(this, "RegistroExitoso", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "ErrorInesperado", Toast.LENGTH_LONG).show()
            }
        }

        val errorListener = Response.ErrorListener {error ->
            Log.e("ErrorListener", error.message.toString())
        }
        val request = JsonObjectRequest(metodo, url, body, listener, errorListener)
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Actualizar?")
        builder.setMessage("Estas seguro?")
        builder.setNegativeButton("SI"){result,_ ->
            queue.add(request)
        }
        builder.setPositiveButton("No"){result ,_ ->

        }
        builder.show()

    }

    fun desactivarLabs(id : Long){
        val queue = Volley.newRequestQueue(this)
        val metodo = Request.Method.PUT
        val url = "http://192.168.106.3/api/labsModi/"+id
        val listener = Response.Listener<JSONObject> { result ->
            if(result.getJSONArray("metadata").getJSONObject(0).getString("codigo").equals("00")){
                Toast.makeText(this, "DesactivacionCorrecta", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "ErrorInesperado", Toast.LENGTH_LONG).show()
            }
        }
        val errorListener = Response.ErrorListener { e ->
            Log.e("Error", e.message.toString())
        }
        val request = JsonObjectRequest(metodo, url, null, listener, errorListener)
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

            }
            R.id.itmSalirLab->{

            }
        }
        return super.onOptionsItemSelected(item)
    }




}