package com.example.fitnessapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditProfileActivity : AppCompatActivity() {

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var saveProfileButton: Button
    private lateinit var userId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // Inizializzazione delle view
        firstNameEditText = findViewById(R.id.firstNameEditText)
        lastNameEditText = findViewById(R.id.lastNameEditText)
        heightEditText = findViewById(R.id.heightEditText)
        weightEditText = findViewById(R.id.weightEditText)
        saveProfileButton = findViewById(R.id.saveProfileButton)

        // Ottieni l'ID dell'utente attualmente loggato
        val currentUser = FirebaseAuth.getInstance().currentUser
        userId = currentUser?.uid ?: return

        // Carica i dati iniziali dal Firestore
        loadProfileData()

        // Imposta il listener per il bottone di salvataggio
        saveProfileButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString()
            val lastName = lastNameEditText.text.toString()
            val height = heightEditText.text.toString()
            val weight = weightEditText.text.toString()

            // Salva i dati su Firestore
            saveProfileDataToFirestore(firstName, lastName, height, weight)

            // Restituisci i dati modificati tramite Intent
            val resultIntent = Intent()
            resultIntent.putExtra("firstName", firstName)
            resultIntent.putExtra("lastName", lastName)
            resultIntent.putExtra("height", height)
            resultIntent.putExtra("weight", weight)
            setResult(Activity.RESULT_OK, resultIntent)

            // Chiudi l'activity
            finish()
        }
    }

    private fun saveProfileDataToFirestore(firstName: String, lastName: String, height: String, weight: String) {
        val db = FirebaseFirestore.getInstance()
        val userData = hashMapOf(
            "firstName" to firstName,
            "lastName" to lastName,
            "height" to height,
            "weight" to weight
        )

        db.collection("users").document(userId)
            .set(userData)
            .addOnSuccessListener {
                Log.d("EditProfile", "Dati aggiornati su Firestore")
            }
            .addOnFailureListener { e ->
                Log.e("EditProfile", "Errore durante il salvataggio su Firestore: ${e.message}")
            }
    }

    private fun loadProfileData() {
        val db = FirebaseFirestore.getInstance()
        db.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    firstNameEditText.setText(document.getString("firstName") ?: "")
                    lastNameEditText.setText(document.getString("lastName") ?: "")
                    heightEditText.setText(document.getString("height") ?: "")
                    weightEditText.setText(document.getString("weight") ?: "")
                } else {
                    Log.w("EditProfile", "Nessun dato trovato su Firestore")
                }
            }
            .addOnFailureListener { e ->
                Log.e("EditProfile", "Errore nel recupero dei dati: ${e.message}")
            }
    }
}
