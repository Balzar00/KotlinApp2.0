package com.example.fitnessapp

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthManager(private val context: Context) {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    // Metodo per ottenere l'utente attualmente loggato
    fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    // Metodo per effettuare il logout e salvare i dati dell'utente corrente
    fun logoutAndSavePreviousUserData() {
        val currentUser = getCurrentUser()
        if (currentUser != null) {
            // Esempio di salvataggio dati utente in SharedPreferences (metodo semplice)
            val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("previousUserId", currentUser.uid)
            editor.putString("previousUserEmail", currentUser.email)
            editor.apply()

            // Ora esegui il logout dall'account corrente
            firebaseAuth.signOut()
            println("Logout eseguito. Dati utente salvati.")
        }
    }

    // Metodo per effettuare il login con un nuovo account
    fun loginWithEmailAndPassword(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Login avvenuto con successo
                    val newUser = getCurrentUser()
                    callback(true, "Login effettuato con il nuovo account: ${newUser?.email}")
                } else {
                    // Login fallito
                    callback(false, task.exception?.message)
                }
            }
    }

    // Metodo per recuperare i dati dell'account precedente (se necessario)
    fun getPreviousUserData(): Pair<String?, String?> {
        val sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE)
        val previousUserId = sharedPreferences.getString("previousUserId", null)
        val previousUserEmail = sharedPreferences.getString("previousUserEmail", null)
        return Pair(previousUserId, previousUserEmail)
    }
}