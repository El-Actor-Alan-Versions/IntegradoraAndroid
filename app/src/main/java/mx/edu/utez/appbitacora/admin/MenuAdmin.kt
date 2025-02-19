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
import mx.edu.utez.appbitacora.laboratorio.RegistrarLabs
import mx.edu.utez.appbitacora.registro.MainActivity

import mx.edu.utez.appbitacora.laboratorio.MostrarLabs
import mx.edu.utez.appbitacora.equipos.MostrarEquipos
import mx.edu.utez.appbitacora.equipos.RegistroEquipo
import mx.edu.utez.appbitacora.registro.MostrarRegistros




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
        binding.cardButton3.startAnimation(animation)
        binding.cardButton4.startAnimation(animation)
        binding.cardButton5.startAnimation(animation)
        binding.cardButton6.startAnimation(animation)
        binding.cardButton8.startAnimation(animation)
        binding.cardButton9.startAnimation(animation)




        val cardButton1 = findViewById<CardView>(R.id.cardButton1)
        val cardButton2 = findViewById<CardView>(R.id.cardButton2)
        val cardButton3 = findViewById<CardView>(R.id.cardButton3)
        val cardButton4 = findViewById<CardView>(R.id.cardButton4)
        val cardButton5 = findViewById<CardView>(R.id.cardButton5)
        val cardButton6 = findViewById<CardView>(R.id.cardButton6)
        val cardButton8 = findViewById<CardView>(R.id.cardButton8)
        val cardButton9 = findViewById<CardView>(R.id.cardButton9)


        cardButton9.setOnClickListener{
            val intnet = Intent(this, MostrarRegistros::class.java)
            intnet.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intnet)
        }

        cardButton3.setOnClickListener{
            val intnet = Intent(this, RegistrarLabs::class.java)
            intnet.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intnet)
        }

        //Ver labs
        cardButton4.setOnClickListener{
            val intnet = Intent(this, MostrarLabs::class.java)
            intnet.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intnet)
        }

        //ver equipos
        cardButton5.setOnClickListener{
            val intnet = Intent(this, MostrarEquipos::class.java)
            intnet.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intnet)
        }


        //Registrar Equipos
        cardButton6.setOnClickListener{
            val intnet = Intent(this, RegistroEquipo::class.java)
            intnet.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intnet)
        }





        cardButton8.setOnClickListener {
            finish() // Cierra la actividad actual
        }



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