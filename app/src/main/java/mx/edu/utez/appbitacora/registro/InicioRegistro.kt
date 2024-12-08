package mx.edu.utez.appbitacora.registro

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.edu.utez.appbitacora.R
import mx.edu.utez.appbitacora.databinding.ActivityInicioRegistroBinding

class InicioRegistro : AppCompatActivity() {
    private lateinit var binding : ActivityInicioRegistroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInicioRegistroBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_inicio_registro)

    }
}