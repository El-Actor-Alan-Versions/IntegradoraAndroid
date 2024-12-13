package mx.edu.utez.appbitacora

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.edu.utez.appbitacora.admin.MenuAdmin
import mx.edu.utez.appbitacora.databinding.ActivityPrincipalBinding
import mx.edu.utez.appbitacora.laboratorio.MenuLabs

class Principal : AppCompatActivity() {
    private lateinit var binding : ActivityPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnIniciarSesionn.setOnClickListener{
        val intnt = Intent(this, IniciarSesion::class.java)
        startActivity(intnt)
        }

        binding.btnSalirOut.setOnClickListener{
            finish ()
        }
    }
}