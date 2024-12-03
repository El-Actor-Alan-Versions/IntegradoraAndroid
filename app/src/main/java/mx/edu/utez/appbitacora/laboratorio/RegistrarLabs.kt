package mx.edu.utez.appbitacora.laboratorio

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.edu.utez.appbitacora.R
import mx.edu.utez.appbitacora.databinding.ActivityRegistrarLabsBinding

class RegistrarLabs : AppCompatActivity() {
    private lateinit var binding : ActivityRegistrarLabsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarLabsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.bottomAppBar)


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