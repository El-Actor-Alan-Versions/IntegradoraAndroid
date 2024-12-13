package mx.edu.utez.appbitacora.laboratorio

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
import mx.edu.utez.appbitacora.databinding.ActivityRegistrarLabsBinding
import org.json.JSONObject

class RegistrarLabs : AppCompatActivity() {
    private lateinit var binding : ActivityRegistrarLabsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarLabsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.bottomAppBar)

        binding.btnRegistrarLabs.setOnClickListener {
            val nombreLab = binding.edtNomLab.text.toString()
            val docencia = binding.edtDocencia.text.toString()
            if (!nombreLab.isNullOrEmpty() && !docencia.isNullOrEmpty()){
                registrarLabs(nombreLab, docencia)
            }
        }

    }

    fun registrarLabs(nom : String, doce : String){
        val queue = Volley.newRequestQueue(this)
        val metodo = Request.Method.POST
        val url = "http://192.168.100.5:8080/api/labs"
        val body = JSONObject()
        body.put("nombre_lab", nom)
        body.put("docencia", doce.toInt())
        body.put("estatus", true)

        val listener = Response.Listener<JSONObject> { r->
            Toast.makeText(this, "Bien", Toast.LENGTH_SHORT).show()
        }
        val errorListener = Response.ErrorListener { e ->
            Log.e("ErrorInset", e.message.toString())
            Log.e("body", body.toString())
            Toast.makeText(this, "Mal", Toast.LENGTH_SHORT).show()
        }
        val request = JsonObjectRequest(metodo, url, body, listener,errorListener)

        queue.add(request)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_labs, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itmRegistraLab->{

            }
            R.id.itmMostrarLab->{

            }
            R.id.itmSalirLab ->{

            }
        }
        return super.onOptionsItemSelected(item)
    }

}