package mx.edu.utez.appbitacora.admin

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import mx.edu.utez.appbitacora.R
import mx.edu.utez.appbitacora.databinding.ActivityMenuAdminBinding
import androidx.cardview.widget.CardView
import android.view.animation.AnimationUtils


class MenuAdmin : AppCompatActivity() {
    private lateinit var binding: ActivityMenuAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.bottomAppBar5)
        // Cargar la animación
        val animation = AnimationUtils.loadAnimation(this, R.anim.entrada_cards)

        // Aplicar la animación al componente 'mainMenu1'
        binding.cardButton1.startAnimation(animation)
        binding.cardButton2.startAnimation(animation)

        // Configuración de las CardViews (Botones)
        val cardButton1 = findViewById<CardView>(R.id.cardButton1)
        val cardButton2 = findViewById<CardView>(R.id.cardButton2)

        // Configura las acciones de los botones
        cardButton1.setOnClickListener {
            onCardClick1()
        }

        cardButton2.setOnClickListener {
            onCardClick2()
        }


    }

    private fun onCardClick1() {
        val intent = Intent(this, InsercionAlumnos::class.java)
        startActivity(intent)
    }
    private fun onCardClick2() {
        val intent = Intent(this, MostrarUsuarios::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_admin, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.itmMostrar ->{
                val intent = Intent(this, MostrarUsuarios::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            R.id.itmRegistro ->{
                val intent = Intent(this, InsercionAlumnos::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
            R.id.itmInicio ->{
                val intent = Intent(this, MenuAdmin::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}