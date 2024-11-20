package com.example.fitnessapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.fitnessapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var authManager: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inizializzazione dell'AuthManager
        authManager = AuthManager(this)

        // Recupera l'utente attualmente loggato
        val currentUser = authManager.getCurrentUser()

        if (currentUser != null) {
            // Verifica se l'utente loggato è cambiato
            checkAndHandleUserChange(currentUser)

            // Mostra un messaggio di benvenuto
            Toast.makeText(this, "Bentornato, ${currentUser.email}!", Toast.LENGTH_SHORT).show()

            // Imposta il fragment della Home solo per utenti loggati
            replaceFragment(Home())
        } else {
            // Se nessun utente è loggato, reindirizza direttamente alla schermata di benvenuto
            navigateToWelcomeScreen()
            return
        }

        // Gestione del menu
        binding.bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(Home())
                R.id.profilo -> replaceFragment(Profilo())
                R.id.scheda_diario -> replaceFragment(Scheda())
                else -> {}
            }
            true
        }

        // Bottone del logout
        val logout: View? = findViewById(R.id.logout)
        logout?.setOnClickListener {
            showExitConfirmationDialog()
        }
    }

    // Funzione per verificare se l'utente loggato è diverso dall'utente precedente
    private fun checkAndHandleUserChange(currentUser: FirebaseUser) {
        authManager.saveUserDataToFirestore(currentUser) { success, message ->
            if (success) {
                Toast.makeText(this, "Dati utente salvati su Firestore.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Errore: $message", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Funzione per mostrare il dialog di conferma del logout
    private fun showExitConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Vuoi tornare alla schermata di Benvenuto?")
            .setPositiveButton("Sì") { _, _ ->
                // Esegui il logout e salva i dati
                logoutAndSaveUserData()
            }
            .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
        builder.create().show()
    }

    // Funzione per eseguire il logout e salvare i dati
    private fun logoutAndSaveUserData() {
        val db = FirebaseFirestore.getInstance()
        val currentUser = authManager.getCurrentUser()

        if (currentUser != null) {
            db.collection("users").document(currentUser.uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val firstName = document.getString("firstName") ?: ""
                        val lastName = document.getString("lastName") ?: ""
                        val height = document.getString("height") ?: ""
                        val weight = document.getString("weight") ?: ""
                        val profileImageUri = document.getString("profileImageUri") ?: ""

                        // Esegui il logout da Firebase
                        FirebaseAuth.getInstance().signOut()

                        // Salva i dati dell'utente precedentemente loggato
                        authManager.logoutAndSavePreviousUserData(
                            firstName = firstName,
                            lastName = lastName,
                            height = height,
                            weight = weight,
                            profileImageUri = profileImageUri
                        )

                        // Naviga alla schermata di benvenuto
                        navigateToWelcomeScreen()
                    } else {
                        Toast.makeText(this, "Impossibile recuperare i dati dell'utente.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Errore: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            // Se non c'è un utente loggato, segnalalo e vai alla schermata di benvenuto
            Toast.makeText(this, "Nessun utente loggato trovato.", Toast.LENGTH_SHORT).show()
            navigateToWelcomeScreen()
        }
    }

    // Funzione per tornare alla schermata di benvenuto
    private fun navigateToWelcomeScreen() {
        val intent = Intent(this, WelcomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()  // Chiudi l'activity corrente per non permettere il ritorno con il back
    }

    // Funzione per sostituire i fragment della home
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}
