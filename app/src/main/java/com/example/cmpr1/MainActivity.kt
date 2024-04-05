package com.example.cmpr1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var intent: Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent = Intent(this, PagoPrAct:: class.java)
        enableEdgeToEdge()
        setContentView(R.layout.order_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun btIrPagofun(view: View) {
        startActivity(intent)
    }
    fun btGasfun(view: View) {
        Toast.makeText(this, resources.getString(R.string.gas_secl), Toast.LENGTH_SHORT).show()
        val rndpay1 = Random.nextInt(0,3000)
        val params = Bundle()
        params.putInt("gas", rndpay1)
        //val intent = Intent(this, PagoPrAct::class.java)
        intent.putExtras(params)
        //startActivity(intent)
    }
    fun btAguafun(view: View) {
        Toast.makeText(this, resources.getString(R.string.agua_secl), Toast.LENGTH_SHORT).show()
        val rndpay2 = Random.nextInt(0,500)
        val params = Bundle()
        params.putInt("agua", rndpay2)
        //val intent = Intent(this, PagoPrAct::class.java)
        intent.putExtras(params)
        //startActivity(intent)
    }
    fun btIntfun(view: View) {
        Toast.makeText(this, resources.getString(R.string.inter_secl), Toast.LENGTH_SHORT).show()
        val rndpay3 = Random.nextInt(0,1000)
        val params = Bundle()
        params.putInt("inter", rndpay3)
        //val intent = Intent(this, PagoPrAct::class.java)
        intent.putExtras(params)
        //startActivity(intent)
    }
    fun btElecfun(view: View) {
        Toast.makeText(this, resources.getString(R.string.elec_secl), Toast.LENGTH_SHORT).show()
        val rndpay4 = Random.nextInt(0,300)
        val params = Bundle()
        params.putInt("elec", rndpay4)
        //val intent = Intent(this, PagoPrAct::class.java)
        intent.putExtras(params)
        //startActivity(intent)
    }

}