package com.example.fitnessapp

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class AuthManager(private val context: Context) {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    
    // Metodo per ottenere l'utente attualmente loggato
    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    // Metodo per effettuare il logout e salvare i dati dell'utente corrente su Firestore
    fun logoutAndSavePreviousUserData(
        firstName: String,
        lastName: String,
        height: String,
        weight: String,
        profileImageUri: String?
    ) {
        val currentUser = getCurrentUser()
        if (currentUser != null) {
            val userId = currentUser.uid

            // Creiamo una mappa con i dati da salvare su Firestore
            val userData = hashMapOf(
                "firstName" to firstName,
                "lastName" to lastName,
                "height" to height,
                "weight" to weight,
                "profileImageUri" to profileImageUri, // L'immagine se disponibile
                "lastLogin" to System.currentTimeMillis() // Salviamo anche l'ora dell'ultimo accesso
            )

            // Salva i dati nel documento dell'utente in Firestore
            val db = FirebaseFirestore.getInstance()
            db.collection("users").document(userId)
                .set(userData, SetOptions.merge()) // Usa merge per aggiornare i dati senza sovrascrivere tutto
                .addOnSuccessListener {
                    Log.d("Firestore", "Dati salvati con successo per l'utente $userId")
                    FirebaseAuth.getInstance().signOut() // Effettua il logout
                    Log.d("Logout", "Utente disconnesso correttamente")
                }
                .addOnFailureListener { exception ->
                    Log.e("Firestore", "Errore nel salvare i dati: ${exception.message}")
                }
        } else {
            Log.w("Logout", "Nessun utente attualmente loggato.")
        }
    }

    // Metodo per effettuare il login con un nuovo account
    fun loginWithEmailAndPassword(
        email: String,
        password: String,
        callback: (Boolean, String?) -> Unit
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login avvenuto con successo
                    val newUser = getCurrentUser()
                    callback(true, "Login effettuato : ${newUser?.email}")
                } else {
                    // Login fallito
                    callback(false, task.exception?.message)
                }
            }
    }

    // Metodo per salvare i dati dell'utente corrente su Firestore
    fun saveUserDataToFirestore(user: FirebaseUser, callback: (Boolean, String?) -> Unit) {
        val userData = hashMapOf(
            "userId" to user.uid,
            "email" to user.email,
                    )

        firestore.collection("users").document(user.uid)
            .set(userData, SetOptions.merge())
            .addOnSuccessListener {
                callback(true, "Dati salvati correttamente.")
            }
            .addOnFailureListener { e ->
                callback(false, e.message)
            }
    }
}
