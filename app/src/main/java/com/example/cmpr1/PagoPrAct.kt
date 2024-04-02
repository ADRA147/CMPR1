package com.example.cmpr1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cmpr1.databinding.ActivityPagoPrBinding
import kotlin.random.Random

class PagoPrAct : AppCompatActivity() {

    private lateinit var binding: ActivityPagoPrBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagoPrBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val params = intent.extras

        if(params!=null){
            val subtot1 = params.getInt("gas", 0)
            val subtot2 = params.getInt("agua", 0)
            val subtot3 = params.getInt("inter", 0)
            val subtot4 = params.getInt("elec", 0)
            val totalCta = subtot1+subtot2+subtot3+subtot4
            binding.tvResTot.text = "$totalCta"
        }

        binding.btEfecPago.setOnClickListener(){
            if (binding.etNums.text.isEmpty()){
                Toast.makeText(this, "Complete la información solicitada", Toast.LENGTH_LONG).show()
                binding.etNums.error = "Campo requerido"
                binding.etNums.requestFocus()
            } else if (binding.etDate.text.isEmpty()) {
                Toast.makeText(this, "Complete la información solicitada", Toast.LENGTH_LONG).show()
                binding.etDate.error = "Campo requerido"
                binding.etDate.requestFocus()
            } else if (binding.etCVV.text.isEmpty()){
                Toast.makeText(this, "Complete la información solicitada", Toast.LENGTH_LONG).show()
                binding.etCVV.error = "Campo requerido"
                binding.etCVV.requestFocus()
            } else if (binding.etNomTH.text.isEmpty()) {
                Toast.makeText(this, "Complete la información solicitada", Toast.LENGTH_LONG).show()
                binding.etNomTH.error = "Campo requerido"
                binding.etNomTH.requestFocus()
            } else if (binding.etCorreo.text.isEmpty()){
                Toast.makeText(this, "Complete la información solicitada", Toast.LENGTH_LONG).show()
                binding.etCorreo.error = "Campo requerido"
                binding.etCorreo.requestFocus()
            } else if (Random.nextInt(0,3) == 3) {
                Toast.makeText(this, "Operación exitosa", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Fallo en la operación. Intente nuevamente", Toast.LENGTH_LONG).show()
            }
        }
    }
}