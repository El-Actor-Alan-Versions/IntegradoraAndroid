package mx.edu.utez.appbitacora

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.edu.utez.appbitacora.databinding.ActivityMostrarUsuariosBinding

class MostrarUsuarios : AppCompatActivity() {
    private lateinit var binding: ActivityMostrarUsuariosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}