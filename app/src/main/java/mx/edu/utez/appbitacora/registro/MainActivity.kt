package mx.edu.utez.appbitacora.registro

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import mx.edu.utez.appbitacora.R
import mx.edu.utez.appbitacora.admin.InsercionAlumnos
import mx.edu.utez.appbitacora.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //Variable publica para poder almacenar el valor a mandar
    var valorQr = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.bottomAppBar3)


        binding.btnInsertar.setOnClickListener {
            val intel = Intent(this,InsercionAlumnos::class.java)
            startActivity(intel)
        }

        binding.btnScan.setOnClickListener {
            //Esto es para configurar el scan
            val options = ScanOptions()
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES)
            options.setPrompt("Scan a barcode")
            options.setCameraId(0) // Use a specific camera of the device
            options.setBeepEnabled(false)
            options.setBarcodeImageEnabled(true)
            barcodeLauncher.launch(options)


        }
    }

    // Register the launcher and result handler
    private val barcodeLauncher = registerForActivityResult<ScanOptions, ScanIntentResult>(
        ScanContract()
    ) { result: ScanIntentResult ->
        //Para revisar si se se encontro algo, o si le dan retroceder
        if (result.contents == null) {
            Toast.makeText(this@MainActivity, "Cancelled", Toast.LENGTH_LONG).show()
        } else {

            //Para mandar a la sig pagina
            valorQr = result.contents;
            val intent = Intent(this, InicioRegistro::class.java)
            intent.putExtra("qr_result",valorQr)
            startActivity(intent)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_registro, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

        }
        return super.onOptionsItemSelected(item)
    }
}