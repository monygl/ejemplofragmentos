package com.example.ejemplofragmentos

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Detalles : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)


        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){

            finish()
            return
        }

        if (savedInstanceState == null) {
            val fDetalles=fragcontenido()
            fDetalles.arguments=intent.extras
            supportFragmentManager.beginTransaction().add(R.id.container,fDetalles).commit()
        }
    }
}

