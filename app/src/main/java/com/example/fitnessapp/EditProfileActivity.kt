package com.example.fitnessapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth


class EditProfileActivity : AppCompatActivity() {

    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var weightEditText: EditText
    private lateinit var saveProfileButton: Button
    private lateinit var userId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        firstNameEditText = findViewById(R.id.firstNameEditText)
        lastNameEditText = findViewById(R.id.lastNameEditText)
        heightEditText = findViewById(R.id.heightEditText)
        weightEditText = findViewById(R.id.weightEditText)
        saveProfileButton = findViewById(R.id.saveProfileButton)

        // Ottieni l'ID dell'utente attualmente loggato
        val currentUser = FirebaseAuth.getInstance().currentUser
        userId = currentUser?.uid ?: "default_user"

        loadProfileData()

        saveProfileButton.setOnClickListener {
            saveProfileData()
            setResult(Activity.RESULT_OK) // Imposta il risultato come OK
            finish()
        }
    }

    private fun saveProfileData() {
        val sharedPreferences = getSharedPreferences("ProfileData_$userId", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("firstName", firstNameEditText.text.toString())
        editor.putString("lastName", lastNameEditText.text.toString())
        editor.putString("height", heightEditText.text.toString())
        editor.putString("weight", weightEditText.text.toString())
        editor.apply()
    }

    private fun loadProfileData() {
        val sharedPreferences = getSharedPreferences("ProfileData_$userId", Context.MODE_PRIVATE)

        val firstName = sharedPreferences.getString("firstName", "")
        val lastName = sharedPreferences.getString("lastName", "")
        val height = sharedPreferences.getString("height", "")
        val weight = sharedPreferences.getString("weight", "")

        firstNameEditText.setText(firstName)
        lastNameEditText.setText(lastName)
        heightEditText.setText(height)
        weightEditText.setText(weight)
    }
}