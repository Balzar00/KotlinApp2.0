package com.example.fitnessapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class SchedaMedioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_scheda_medio)

        val bottoneGg1 =findViewById<Button>(R.id.medio1btn)
        val bottoneGg2 =findViewById<Button>(R.id.medio2btn)
        val bottoneGg3 =findViewById<Button>(R.id.medio3btn)


        bottoneGg1.setOnClickListener({
            replaceFragment(MedioGg1())
        })
        bottoneGg2.setOnClickListener({
            replaceFragment(MedioGg2())
        })
        bottoneGg3.setOnClickListener({
            replaceFragment(MedioGg3())
        })


    }
    private fun replaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout,fragment).commit()
    }
}