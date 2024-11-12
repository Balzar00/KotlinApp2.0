package com.example.fitnessapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fitnessapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var authManager: AuthManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        replaceFragment(Home())

        //inizializzazione dell' AuthManager
        authManager = AuthManager(this)


        //Gestione del menu
        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {

                R.id.home -> replaceFragment(Home())
                R.id.profilo -> replaceFragment(Profilo())
                R.id.scheda_diario -> replaceFragment(Scheda())
                else -> {}
            }
            true
        }

        //bottone del logout
        val logout: View? = findViewById(R.id.logout)
        if (logout != null) {
            logout.setOnClickListener {
                showExitConfirmationDialog()
            }
        }

    }

    //funzione per mostrare il dialog della conferma di logout
    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Vuoi tornare alla schermata di Benvenuto?")
            .setPositiveButton("Sì") { dialog, id ->
                //eseguire il logout e salvare i dati dell'utente precedente
                //FirebaseAuth.getInstance().signOut()
                authManager.logoutAndSavePreviousUserData()
                //passare alla schermata di benvenuto se si clicca SI
                val intent = Intent(this, WelcomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
                //rimanere nella home se si clicca no
            .setNegativeButton("No") { dialog, id -> dialog.dismiss()
            }

        builder.create().show()
    }

    //funzione per sostituire i fragment della home
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}