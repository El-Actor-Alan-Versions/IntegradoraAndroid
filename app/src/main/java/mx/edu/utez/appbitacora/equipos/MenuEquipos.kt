package mx.edu.utez.appbitacora.equipos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.edu.utez.appbitacora.R
import mx.edu.utez.appbitacora.databinding.ActivityMenuEquiposBinding

class MenuEquipos : AppCompatActivity() {
    private lateinit var binding : ActivityMenuEquiposBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuEquiposBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}