package com.example.fitnessapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Profilo : Fragment() {

    private lateinit var profileName: TextView
    private lateinit var profileSurname: TextView
    private lateinit var profileWeight: TextView
    private lateinit var profileHeight: TextView
    private lateinit var profileImage: ImageButton
    private lateinit var editProfileButton: Button
    private lateinit var userId: String

    companion object {
        private const val REQUEST_CODE_EDIT_PROFILE = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profilo, container, false)

        // Inizializzazione delle view
        profileImage = view.findViewById(R.id.profileImage)
        profileName = view.findViewById(R.id.profileName)
        profileSurname = view.findViewById(R.id.profileSurname)
        profileWeight = view.findViewById(R.id.profileWeight)
        profileHeight = view.findViewById(R.id.profileHeight)
        editProfileButton = view.findViewById(R.id.editProfileButton)

        // Ottieni l'utente attualmente loggato
        val currentUser = FirebaseAuth.getInstance().currentUser
        userId = currentUser?.uid ?: return view

        // Carica i dati iniziali
        loadProfileData()

        // Bottone per modificare il profilo
        editProfileButton.setOnClickListener {
            val intent = Intent(activity, EditProfileActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_EDIT_PROFILE)
        }

        return view
    }

    private fun loadProfileData() {
        val db = FirebaseFirestore.getInstance()

        db.collection("users").document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val firstName = document.getString("firstName") ?: "Nome"
                    val lastName = document.getString("lastName") ?: "Cognome"
                    val height = document.getString("height") ?: "Altezza"
                    val weight = document.getString("weight") ?: "Peso"
                    updateProfileData(firstName, lastName, height, weight)
                } else {
                    Log.w("Profilo", "Nessun dato trovato per l'utente.")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Errore nel recuperare i dati del profilo: ${exception.message}")
            }
    }

     fun updateProfileData(firstName: String, lastName: String, height: String, weight: String) {
        profileName.text = firstName
        profileSurname.text = lastName
        profileHeight.text = "Altezza: $height cm"
        profileWeight.text = "Peso: $weight kg"
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_EDIT_PROFILE && resultCode == Activity.RESULT_OK) {
            // Aggiorna immediatamente la UI
            data?.let {
                val firstName = it.getStringExtra("firstName") ?: "Nome"
                val lastName = it.getStringExtra("lastName") ?: "Cognome"
                val height = it.getStringExtra("height") ?: "Altezza"
                val weight = it.getStringExtra("weight") ?: "Peso"
                updateProfileData(firstName, lastName, height, weight)
            }

            // Ricarica i dati da Firestore per conferma
            loadProfileData()
        }
    }
}
