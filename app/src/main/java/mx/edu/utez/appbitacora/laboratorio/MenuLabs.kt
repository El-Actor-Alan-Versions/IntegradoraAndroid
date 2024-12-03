package mx.edu.utez.appbitacora.laboratorio

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.edu.utez.appbitacora.R
import mx.edu.utez.appbitacora.databinding.ActivityMenuLabsBinding

class MenuLabs : AppCompatActivity() {
    private lateinit var binding : ActivityMenuLabsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuLabsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar5)

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