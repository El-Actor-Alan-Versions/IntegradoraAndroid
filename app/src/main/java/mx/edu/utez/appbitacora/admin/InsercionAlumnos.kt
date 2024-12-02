package mx.edu.utez.appbitacora.admin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.edu.utez.appbitacora.R
import mx.edu.utez.appbitacora.databinding.ActivityInsercionAlumnosBinding

class InsercionAlumnos : AppCompatActivity() {
    private lateinit var binding : ActivityInsercionAlumnosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsercionAlumnosBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}