package com.example.fitnessapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class Scheda() : Fragment(){


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_scheda, container, false)

        val bottonePrincipiante : Button = view.findViewById(R.id.btn_principiante)
        val bottoneMedio : Button = view.findViewById(R.id.btn_medio)
        val bottoneAvanzato : Button = view.findViewById(R.id.btn_avanzato)


        bottonePrincipiante.setOnClickListener {
            val intent = Intent(activity, SchedaPrincipianteActivity::class.java)
            startActivity(intent)
        }

        bottoneMedio.setOnClickListener {
            val intent = Intent(activity, SchedaMedioActivity::class.java)
            startActivity(intent)
        }

        bottoneAvanzato.setOnClickListener {
            val intent = Intent(activity, SchedaAvanzatoActivity::class.java)
            startActivity(intent)
        }
        return view
    }
}