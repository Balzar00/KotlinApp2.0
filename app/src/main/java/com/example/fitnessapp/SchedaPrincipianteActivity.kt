package com.example.fitnessapp

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class SchedaPrincipianteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_scheda_principiante)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bottoneGg1 =findViewById<Button>(R.id.giorno1btn)
        val bottoneGg2 =findViewById<Button>(R.id.giorno2btn)
        val bottoneGg3 =findViewById<Button>(R.id.giorno3btn)


        bottoneGg1.setOnClickListener({
            replaceFragment(PrincipianteGg1())
        })
        bottoneGg2.setOnClickListener({
            replaceFragment(PrincipianteGg2())
        })
        bottoneGg3.setOnClickListener({
            replaceFragment(PrincipianteGg3())
        })
    }

    private fun replaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit()
    }
}