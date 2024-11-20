package com.example.fitnessapp

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class SchedaAvanzatoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_scheda_avanzato)

        val bottoneGg1 =findViewById<Button>(R.id.avanzato1btn)
        val bottoneGg2 =findViewById<Button>(R.id.avanzato2btn)
        val bottoneGg3 =findViewById<Button>(R.id.avanzato3btn)

        bottoneGg1.setOnClickListener({
            replaceFragment(AvanzatoGg1())
        })

        bottoneGg2.setOnClickListener({
            replaceFragment(AvanzatoGg2())
        })

        bottoneGg3.setOnClickListener({
            replaceFragment(AvanzatoGg3())
        })

    }
    private fun replaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit()
    }
}