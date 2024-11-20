package com.example.fitnessapp

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var authManager: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inizializza AuthManager
        authManager = AuthManager(this)

        // Gestione del click sul bottone di login
        binding.continueBtn.setOnClickListener {
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()

            // Verifica che l'email e la password non siano vuote
            if (email.isNotEmpty() && password.isNotEmpty()) {
                authManager.loginWithEmailAndPassword(email, password) { isSuccess, message ->
                    if (isSuccess) {
                        // Login avvenuto con successo
                        Log.d(ContentValues.TAG, "signInWithEmail:success")
                        Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()

                        // Dopo il login, carica i dati dell'utente da Firestore
                        val user = FirebaseAuth.getInstance().currentUser
                        if (user != null) {
                            loadProfileDataFromFirestore(user)
                        }

                        // Passa alla MainActivity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish() // Chiudi questa activity per non tornare indietro al login
                    } else {
                        // Login fallito, mostra il messaggio di errore
                        Log.w(ContentValues.TAG, "signInWithEmail:failure")
                        Toast.makeText(baseContext, message ?: "Accesso fallito.", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(baseContext, "Per favore, inserisci email e password.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadProfileDataFromFirestore(user: FirebaseUser) {
        val db = FirebaseFirestore.getInstance()
        val docRef = db.collection("users").document(user.uid)

        // Carica i dati del profilo da Firestore
        docRef.get().addOnSuccessListener { document ->
            if (document != null && document.exists()) {
                val firstName = document.getString("firstName") ?: ""
                val lastName = document.getString("lastName") ?: ""
                val height = document.getString("height") ?: ""
                val weight = document.getString("weight") ?: ""

                // Aggiorna i dati del profilo, ad esempio nel Fragment del profilo
                updateProfile(firstName, lastName, height, weight)
            }
        }.addOnFailureListener { e ->
            Log.w(ContentValues.TAG, "Errore nel recupero dei dati.", e)
        }
    }

    private fun updateProfile(firstName: String, lastName: String, height: String, weight: String) {
        // Aggiorna i dati del profilo nel UI, ad esempio in un Fragment
        val profileFragment = supportFragmentManager.findFragmentByTag("ProfileFragment") as? Profilo
        profileFragment?.updateProfileData(firstName, lastName, height, weight)
    }
}
