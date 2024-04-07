package com.example.cmpr1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cmpr1.databinding.ActivityPagoPrBinding
import com.google.android.material.textfield.TextInputLayout
import java.text.DecimalFormat
import kotlin.random.Random

class PagoPrAct : AppCompatActivity() {

    private lateinit var binding: ActivityPagoPrBinding
    private  lateinit var spinnerTJ: Spinner
    private  lateinit var spnMM: Spinner
    private  lateinit var spnYY: Spinner
    private  lateinit var etMail: EditText
    private lateinit var etLayEd: TextInputLayout
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
        spinnerTJ= findViewById(R.id.spinnerTJ)
        val tjnames = resources.getStringArray(R.array.tj_names)
        val adapTJ = ArrayAdapter(this, android.R.layout.simple_spinner_item, tjnames)
        adapTJ.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinnerTJ.adapter = adapTJ

        spnMM= findViewById(R.id.spnMes)
        val datemes = resources.getStringArray(R.array.date_mes)
        val adapMM = ArrayAdapter(this, android.R.layout.simple_spinner_item, datemes)
        adapMM.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spnMM.adapter = adapMM

        spnYY= findViewById(R.id.spnAnn)
        val dateYY = resources.getStringArray(R.array.date_ann)
        val adapYY = ArrayAdapter(this, android.R.layout.simple_spinner_item, dateYY)
        adapYY.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spnYY.adapter = adapYY

        etMail = findViewById(R.id.etMail)
        etLayEd = findViewById(R.id.tiLayEmail)
        etMail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val isValid = s?.let { isValidEmail(it.toString()) } ?: false
                etLayEd.error = if (!isValid) getString(R.string.pg_correo_error) else null
            }
        })
        val params = intent.extras

        if(params!=null){
            val subtot1 = params.getInt("gas", 0)
            val subtot2 = params.getInt("agua", 0)
            val subtot3 = params.getInt("inter", 0)
            val subtot4 = params.getInt("elec", 0)
            val totalCta = subtot1+subtot2+subtot3+subtot4
            val decFormat = DecimalFormat("$ ####,####.00")
            binding.tvResTot.text = decFormat.format(totalCta)
        }

        binding.btEfecPago.setOnClickListener{
            val emailck = etMail.text.toString()
            val emEmpty = emailck.isEmpty()
            if (binding.etNums.text.isEmpty()){
                Toast.makeText(this, resources.getString(R.string.ask_info), Toast.LENGTH_LONG).show()
                binding.etNums.error = getString(R.string.req_inf)
                binding.etNums.requestFocus()
            } else if (binding.etCVV.text.isEmpty()){
                Toast.makeText(this, resources.getString(R.string.ask_info), Toast.LENGTH_LONG).show()
                binding.etCVV.error = getString(R.string.req_inf)
                binding.etCVV.requestFocus()
            } else if (binding.etNomTH.text.isEmpty()) {
                Toast.makeText(this, resources.getString(R.string.ask_info), Toast.LENGTH_LONG).show()
                binding.etNomTH.error = getString(R.string.req_inf)
                binding.etNomTH.requestFocus()
            } else if (emEmpty) {
                etLayEd.error = getString(R.string.req_inf)
            } else if (Random.nextInt(0,3) == 3) {
                val intent = Intent(this, OpFailAct::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, OpESAct::class.java)
                startActivity(intent)
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}