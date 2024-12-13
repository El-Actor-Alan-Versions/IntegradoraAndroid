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
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import mx.edu.utez.appbitacora.R
import mx.edu.utez.appbitacora.databinding.ActivityRegistrarLabsBinding
import mx.edu.utez.appbitacora.equipos.RegistroEquipo
import mx.edu.utez.appbitacora.admin.MenuAdmin
import mx.edu.utez.appbitacora.laboratorio.RegistrarLabs
import mx.edu.utez.appbitacora.laboratorio.MostrarLabs

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

        binding.btnOut.setOnClickListener{
            val intnet = Intent(this, MenuAdmin::class.java)
            intnet.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intnet)
        }

    }

    fun registrarLabs(nom : String, doce : String){
        val queue = Volley.newRequestQueue(this)
        val metodo = Request.Method.POST
        val url = "http://192.168.1.68:8080/api/labs"
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