package com.example.fitnessapp

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop


class Profilo : Fragment() {


    private lateinit var profileName: TextView
    private lateinit var profileSurname: TextView
    private lateinit var profileWeight: TextView
    private lateinit var profileHeight :TextView
    private lateinit var profileImage: ImageButton
    private lateinit var editProfileButton: Button
    private lateinit var userId: String //variabile per l'ID utente


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profilo, container, false)

        profileImage = view.findViewById(R.id.profileImage)
        profileName = view.findViewById(R.id.profileName)
        profileSurname = view.findViewById(R.id.profileSurname)
        profileWeight = view.findViewById(R.id.profileWeight)
        profileHeight = view.findViewById(R.id.profileHeight)
        editProfileButton = view.findViewById(R.id.editProfileButton)

        // Ottieni l'ID dell'utente attualmente loggato
        val currentUser = FirebaseAuth.getInstance().currentUser
        userId = currentUser?.uid ?:"default_user"

        editProfileButton.setOnClickListener {
            val intent = Intent(activity, EditProfileActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_EDIT_PROFILE)
        }
        loadProfileData()



        profileImage.setOnClickListener {
            checkStoragePermission() // Verifica i permessi e apri la galleria
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        loadProfileData() // Carica i dati del profilo ogni volta che il Fragment diventa visibile
    }

    private fun loadProfileData() {
        val sharedPreferences = activity?.getSharedPreferences("ProfileData_$userId", Context.MODE_PRIVATE)
        val firstName = sharedPreferences?.getString("firstName", "Nome")
        val lastName = sharedPreferences?.getString("lastName", "Cognome")
        val height = sharedPreferences?.getString("height", "Altezza")
        val weight = sharedPreferences?.getString("weight", "Peso")
        val profileImageUri = sharedPreferences?.getString("profileImageUri", null)


        profileName.text = "$firstName"
        profileSurname.text ="$lastName"
        profileHeight.text = "Altezza: $height cm"
        profileWeight.text = "Peso: $weight kg"

        if (profileImageUri != null) {
            // Usa Glide per caricare l'immagine
            context?.let { safeContext ->
                Glide.with(safeContext)
                    .load(profileImageUri)
                    .transform(CircleCrop())
                    .placeholder(R.drawable.ic_profile)  // Immagine di default mentre si carica
                    .into(profileImage)
            }
        } else {
            profileImage.setImageResource(R.drawable.ic_profile)  // Immagine di default
        }
    }


    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE)

        // Controlla se c'è un'app per aprire la galleria
        val packageManager = requireContext().packageManager
        val galleryIntent = Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        // Filtro per le app di galleria
        val chooserIntent = Intent.createChooser(galleryIntent, "Scegli un'immagine")
        if (chooserIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(chooserIntent, REQUEST_CODE_PICK_IMAGE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_PICK_IMAGE  && resultCode == Activity.RESULT_OK ) {
            val selectedImageUri: Uri? = data?.data
            if (selectedImageUri != null) {
                profileImage.setImageURI(selectedImageUri)

                // Salva l'URI nei SharedPreferences
                val sharedPreferences = activity?.getSharedPreferences("ProfileData_$userId", Context.MODE_PRIVATE)
                sharedPreferences?.edit()?.putString("profileImageUri", selectedImageUri.toString())?.apply()
            }
        }
        if (requestCode == REQUEST_CODE_EDIT_PROFILE && resultCode == Activity.RESULT_OK) {
            loadProfileData()
        }
    }

    private fun checkStoragePermission() {
        Log.d("CheckPermission", "Controllo permesso di accesso alla memoria")
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.d("CheckPermission", "Permesso non concesso, richiedilo")
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                PERMISSION_REQUEST_CODE
            )
        } else {
            Log.d("CheckPermission", "Permesso già concesso, apri la galleria")
            openGallery()
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openGallery()
        }else {
            // Il permesso è stato negato, mostra un messaggio o disabilita l'azione
            Toast.makeText(context, "Permesso negato! Non puoi accedere alla galleria.", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val REQUEST_CODE_EDIT_PROFILE = 1
        private const val REQUEST_CODE_PICK_IMAGE = 2
        private const val PERMISSION_REQUEST_CODE = 3
    }
}





