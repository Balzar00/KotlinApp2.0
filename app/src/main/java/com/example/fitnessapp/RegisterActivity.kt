package com.example.fitnessapp

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fitnessapp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.continueBtn.setOnClickListener{
            auth.createUserWithEmailAndPassword(binding.email.getText().toString().trim(), binding.password.getText()
                .toString().trim())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // La registrazione ha successo e aggiorna l'UI con le info dell'utente corrente
                        Log.d(ContentValues.TAG, "createUserWithEmail:success")
                        Toast.makeText(
                            baseContext,
                            "Registrazione effettuata.",
                            Toast.LENGTH_SHORT,
                        ).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        // Se la registrazione fallisce verrà mostrato un messaggio di errore
                        Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext,
                            "Registrazione fallita.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }
        binding.move.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}